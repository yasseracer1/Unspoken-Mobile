package org.d3if3130.unspoken.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.ui.screen.DetailScreen
import org.d3if3130.unspoken.ui.screen.KEY_ID_CERITA
import org.d3if3130.unspoken.ui.screen.LoginScreen
import org.d3if3130.unspoken.ui.screen.MainScreen
import org.d3if3130.unspoken.ui.screen.PostinganScreen
import org.d3if3130.unspoken.ui.screen.ProfileScreen
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
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Postingan.route) {
            PostinganScreen(navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CERITA) { type = NavType.LongType }
            )
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CERITA)
            DetailScreen(navController, id)
        }
    }
}