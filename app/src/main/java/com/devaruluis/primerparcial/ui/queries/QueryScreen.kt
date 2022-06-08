package com.devaruluis.loanscompose.ui.queries

import IdSearchField
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devaruluis.loanscompose.ui.viewmodel.ViewModel

@Composable
fun QueryBody(
    viewModel: ViewModel = hiltViewModel(), navController: NavController
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Personas", fontSize = 24.sp)
        Box(modifier = Modifier.padding(vertical = 10.dp)) {
            IdSearchField(value = 0, handleValueChange = {}, handleSearchClick = {})
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QueryPreview() {
    val navController = rememberNavController()
    Surface() {
        QueryBody(navController = navController)
    }
}