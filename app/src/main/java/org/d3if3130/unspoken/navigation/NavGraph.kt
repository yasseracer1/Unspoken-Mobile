package org.d3if3130.unspoken.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.google.GoogleSignIn
import org.d3if3130.unspoken.ui.screen.DetailScreen
import org.d3if3130.unspoken.ui.screen.KEY_ID_CERITA
import org.d3if3130.unspoken.ui.screen.KEY_ID_POSTINGAN
import org.d3if3130.unspoken.ui.screen.KomentarScreen
import org.d3if3130.unspoken.ui.screen.LoginScreen
import org.d3if3130.unspoken.ui.screen.MainScreen
import org.d3if3130.unspoken.ui.screen.MembuatPostingan
import org.d3if3130.unspoken.ui.screen.OpenPostingan
import org.d3if3130.unspoken.ui.screen.PostinganScreen
import org.d3if3130.unspoken.ui.screen.ProfileScreen
import org.d3if3130.unspoken.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val googleSignIn = GoogleSignIn(context = context, scope = scope)

    NavHost(
        navController = navController,
        startDestination = if(FirebaseAuth.getInstance().currentUser == null) Screen.Login.route
        else Screen.Home.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController, googleSignIn)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController, currentUser = FirebaseAuth.getInstance().currentUser)
        }
        composable(route = Screen.Postingan.route) {
            PostinganScreen(navController, currentUser = FirebaseAuth.getInstance().currentUser)
        }
        composable(route = Screen.CreatePostingan.route) {
            MembuatPostingan(navController)
        }

        composable(
            route = Screen.OpenPostingan.route,
            arguments = listOf(
                navArgument(KEY_ID_POSTINGAN) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(KEY_ID_POSTINGAN)
            if (id != null) {
                OpenPostingan(navController, id)
            }
        }

        composable(
            route = Screen.BeriKomentar.route,
            arguments = listOf(
                navArgument(KEY_ID_POSTINGAN) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(KEY_ID_POSTINGAN)
            if (id != null) {
                KomentarScreen(navController, currentUser = FirebaseAuth.getInstance().currentUser, id)
            }
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController, currentUser = FirebaseAuth.getInstance().currentUser)
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