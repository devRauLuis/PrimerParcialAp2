package com.devaruluis.loanscompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.primerparcial.database.dao.LoanDao
import com.devaruluis.primerparcial.model.Loan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class UiState(
    var id: Long? = 0L,
    var debtorName: String? = "",
    var concept: String? = "",
    var amount: Float? = 0F,
    val loansList: List<Loan> = listOf(),
    val userMessages: List<String> = listOf(),
    var showSnackbar: Boolean = false, var snackbarMessage: String? = ""
)

fun UiState.toLoan() = Loan(
    id = id ?: 0,
    concept = concept,
    debtorName = debtorName,
    amount = amount
)

@HiltViewModel
class LoanViewModel @Inject constructor(
    val loanDao: LoanDao
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            uiState = try {
                uiState.copy(loansList = loanDao.getAll())
            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState.copy(userMessages = messages)
            }
        }
    }

    private var fetchJob: Job? = null

    fun setId(id: Long? = null) {
        uiState = uiState.copy(id = id ?: 0)
    }

    fun setDebtorName(name: String? = "") {
        uiState = uiState.copy(debtorName = name)
    }

    fun setConcept(concept: String? = "") {
        uiState = uiState.copy(concept = concept)
    }

    fun setAmount(amount: Float? = null) {
        uiState = uiState.copy(
            amount =
            if (amount != null && amount > 0F)
                amount
            else 0F
        )
    }

    fun showSnackbar(message: String) {
        uiState = uiState.copy(showSnackbar = true, snackbarMessage = message)
    }

    fun dismissSnackbar() {
        uiState = uiState.copy(showSnackbar = false, snackbarMessage = null)
    }

    fun save() {
        viewModelScope.launch {
            val loan = uiState.toLoan()
            if (!(loan.debtorName != null && loan.debtorName?.length > 5)) {
                showSnackbar("El nombre del deudor no puede ser tan corto")
            } else if (!(loan.concept != null && loan.concept?.length > 4)) {
                showSnackbar("El concepto no puede ser tan corto")
            } else if (!(loan.amount != null && loan.amount > 0)) {
                showSnackbar("El monto debe ser mayor a 0")
            } else {
                val id =
                    loanDao.insert(loan)
                uiState = uiState.copy(id = id)
                find()
            }
        }
    }

    fun find() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            try {
                val loan = loanDao.find(uiState.id)
                if (loan != null)
                    uiState = uiState.copy(
                        id = loan.id,
                        debtorName = loan.debtorName,
                        concept = loan.concept,
                        amount = loan.amount
                    )

            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState = uiState.copy(userMessages = messages)
            }

        }
    }

    fun delete() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            try {
                val loan = loanDao.find(uiState.id)
                if (loan !== null) {
                    loanDao.delete(loan)
                    new()
                }
            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState = uiState.copy(userMessages = messages)
            }
        }
    }

    fun new() {
        viewModelScope.launch {
            uiState = UiState()
        }
    }


}