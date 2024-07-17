package ui.navigation

sealed class Screen {
    data object Login : Screen()
    data object Home : Screen()
    data class Detail(val itemId: String) : Screen()
}