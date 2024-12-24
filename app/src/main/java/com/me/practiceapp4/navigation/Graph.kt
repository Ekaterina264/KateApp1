package com.me.practiceapp4.navigation

import DetailsScreen
import OrderScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph(startDestination: String = "home") {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("home") { HomeScreen(navController) }
        composable("details/{itemId}") { backStackEntry ->
            DetailsScreen(navController)
        }
        composable("order_details/{order}", arguments = listOf(navArgument("order"){
            defaultValue = "Ничего нет"
        })){ backstackentry ->
            OrderScreen(order = backstackentry.arguments?.getString("order"))
        }
    }
}