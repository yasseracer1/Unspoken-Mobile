package org.d3if3130.mobpro1.navigation

import org.d3if3130.unspoken.ui.screen.KEY_ID_CERITA

sealed class Screen(val route: String) {
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CERITA}") {
        fun withId(id: Long) = "detailScreen/$id"
    }

}