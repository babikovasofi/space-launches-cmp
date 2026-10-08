package ru.omgtu.babikova.spacelaunches

sealed interface Screen {
    data object List : Screen
    data class Detail(val id: String) : Screen
    data class Agency(val id: Int) : Screen
    data class Pad(val id: Int) : Screen
}
