package com.devaruluis.loanscompose.ui.queries

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devaruluis.loanscompose.ui.navigation.Screen
import com.devaruluis.loanscompose.ui.viewmodel.LoanViewModel

@Composable
fun QueryBody(
    viewModel: LoanViewModel = hiltViewModel(), navController: NavController
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Prestamos", fontSize = 24.sp)
            Button(
                onClick = { navController.navigate(Screen.Registry.name) },
            ) { Icon(Icons.Filled.Add, "Agregar") }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            items(uiState.loansList) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = it.id.toString(), fontSize = 16.sp, fontWeight = FontWeight.Black)
                    Column {
                        Text(
                            text = it.debtorName.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = it.concept.toString(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        )

                    }
                    Text(
                        text = "$${it.amount}", fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { navController.navigate("${Screen.Registry.name}/${it.id}") }) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Go")
                    }
                }
                Divider(color = Color.Black)
            }
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
