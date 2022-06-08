package com.devaruluis.loanscompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.devaruluis.loanscompose.ui.HomeScreenBody
import com.devaruluis.loanscompose.ui.queries.QueryBody
import com.devaruluis.loanscompose.ui.registries.RegistryBody

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name,
        modifier = modifier
    ) {
        composable(Screen.Home.name) {
            HomeScreenBody()
        }

        composable(
            Screen.Registry.name
        ) {
            RegistryBody()
        }

        val personRegistryName = Screen.Registry.name
        composable(
            route = "$personRegistryName/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "app://$personRegistryName/{id}"
                }
            ),

            ) {
            val id = it.arguments?.getString("id")
            println("id arg: $id")
            RegistryBody(id=id?.toLongOrNull() ?: 0)
        }


        composable(Screen.Query.name) {
            QueryBody(navController = navController)
        }

    }
}
