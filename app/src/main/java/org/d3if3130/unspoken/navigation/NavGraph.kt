package org.d3if3130.unspoken.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.ui.screen.LoginScreen
import org.d3if3130.unspoken.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
    }
}