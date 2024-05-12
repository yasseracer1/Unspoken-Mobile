package org.d3if3130.mobpro1.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")

}