package com.devaruluis.loanscompose.ui.registries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devaruluis.loanscompose.ui.components.form.RegistryActionButtons
import com.devaruluis.loanscompose.ui.viewmodel.LoanViewModel

@Composable
fun RegistryBody(
    viewModel: LoanViewModel = hiltViewModel(),
    id: Long? = 0L
) {
    var uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    if (uiState.showSnackbar) {
        LaunchedEffect(uiState.showSnackbar) {
            try {
                when (snackbarHostState.showSnackbar(
                    uiState.snackbarMessage.toString(),
                )) {
                    SnackbarResult.Dismissed -> {
                    }
                }
            } finally {
                viewModel.dismissSnackbar()
            }
        }
    }

    LaunchedEffect(id) {
        println("id arg: $id")

        if (id != null && id > 0L) {
            viewModel.setId(id)
            viewModel.find()
        }
    }


    val textFieldModifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .padding(top = 10.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column() {
            Text(text = "Registro", fontSize = 24.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

//                IdSearchField(
//                    value = uiState.id,
//                    handleValueChange = { viewModel.setId(it) },
//                    handleSearchClick = { viewModel.find() })

                TextField(
                    value = uiState.debtorName ?: "",
                    onValueChange = {
                        viewModel.setDebtorName(it)
                    },
                    label = { Text(text = "Deudor") },
                    modifier = textFieldModifier, leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Deudor"
                        )
                    }
                )

                TextField(
                    value = uiState.concept ?: "",
                    onValueChange = {
                        viewModel.setConcept(it)
                    },
                    label = { Text(text = "Concepto") },
                    modifier = textFieldModifier,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Description,
                            contentDescription = "Concepto"
                        )
                    }
                )

                TextField(
                    value = uiState.amount.toString(),
                    onValueChange = {
                        viewModel.setAmount(it.toFloatOrNull() ?: 0F)
                    },
                    label = { Text(text = "Monto") },
                    modifier = textFieldModifier,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Money,
                            contentDescription = "Monto"
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                RegistryActionButtons(
                    handleNewClick = {
                        viewModel.new()
                    },
                    handleSaveClick = {
                        viewModel.save()
                    }, handleDeleteClick = {
                        viewModel.delete()
                    })
            }

            SnackbarHost(hostState = snackbarHostState)

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistryBodyPreview() {
    Surface {
        RegistryBody()
    }
}