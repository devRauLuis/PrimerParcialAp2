package com.devaruluis.loanscompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    var id: Long? = 0L,

    val userMessages: List<String> = listOf()
)

@HiltViewModel
class ViewModel @Inject constructor(
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    init {
//        viewModelScope.launch {
//            uiState = try {
//
//            } catch (ioe: IOException) {
//                val messages = listOf(ioe.message.toString())
//                uiState.copy(userMessages = messages)
//            }
//        }
    }

    private var fetchJob: Job? = null

    fun setId(id: Long? = null) {
        uiState = uiState.copy(id = id ?: 0)
    }


    fun save() {
        viewModelScope.launch {
//            val id =
//                personDao.insert(uiState.toPerson())
//            uiState = uiState.copy(id = id)
//            find()
        }
    }

    fun find() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
//            try {
//                val person = personDao.find(uiState.id)
//                if (person != null)
//                    uiState = uiState.copy(
//                        id = person.id,
//                        names = person.names,
//                        surnames = person.surnames,
//                        income = person.income,
//                        occupation = occupationDao.find(person.occupationId)
//                    )
//
//            } catch (ioe: IOException) {
//                val messages = listOf(ioe.message.toString())
//                uiState = uiState.copy(userMessages = messages)
//            }

        }
    }

    fun delete() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
//            try {
//                val person = personDao.find(uiState.id)
//                if (person !== null) {
//                    personDao.delete(person)
//                    new()
//                }
//            } catch (ioe: IOException) {
//                val messages = listOf(ioe.message.toString())
//                uiState = uiState.copy(userMessages = messages)
//            }
        }
    }

    fun new() {
        viewModelScope.launch {
            uiState = UiState()
        }
    }


}