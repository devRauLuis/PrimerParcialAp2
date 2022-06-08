package com.devaruluis.loanscompose.ui.registries

import IdSearchField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devaruluis.loanscompose.ui.components.form.RegistryActionButtons
import com.devaruluis.loanscompose.ui.viewmodel.ViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegistryBody(
    viewModel: ViewModel = hiltViewModel(),
    id: Long? = 0L
) {
    LaunchedEffect(id){
        println("id arg: $id")

        if (id != null && id > 0L) {
            viewModel.setId(id)
            viewModel.find()
        }
    }


    var uiState = viewModel.uiState

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
                IdSearchField(
                    value = uiState.id,
                    handleValueChange = { viewModel.setId(it) },
                    handleSearchClick = { viewModel.find() })

                RegistryActionButtons(
                    handleNewClick = {
                        viewModel.new()
                    },
                    handleSaveClick = {
                        viewModel.save(

                        )
                    }, handleDeleteClick = {
                        viewModel.delete()
                    })
            }

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