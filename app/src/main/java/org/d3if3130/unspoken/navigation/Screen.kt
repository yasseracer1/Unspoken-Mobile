package org.d3if3130.mobpro1.navigation

import org.d3if3130.unspoken.ui.screen.KEY_ID_CERITA
import org.d3if3130.unspoken.ui.screen.KEY_ID_POSTINGAN

sealed class Screen(val route: String) {
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")
    data object Home: Screen("mainScreen")
    data object OpenPostingan: Screen("openPostingan/{$KEY_ID_POSTINGAN}") {
        fun withId(id: String) = "openPostingan/$id"
    }
    data object Postingan: Screen("postinganScreen")
    data object BeriKomentar: Screen("beriKomentar/{$KEY_ID_POSTINGAN}") {
        fun withId(id: String) = "beriKomentar/$id"
    }
    data object Profile: Screen("profileScreen")
    data object FormBaru: Screen("detailScreen")

    data object CreatePostingan: Screen("createPostingan")


    data object FormUbah: Screen("detailScreen/{$KEY_ID_CERITA}") {
        fun withId(id: Long) = "detailScreen/$id"
    }

}