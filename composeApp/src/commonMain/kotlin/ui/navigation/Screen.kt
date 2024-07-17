package ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Login : Screen()
    @Serializable
    data object Home : Screen()
    @Serializable
    data class Detail(val itemId: String) : Screen()
}