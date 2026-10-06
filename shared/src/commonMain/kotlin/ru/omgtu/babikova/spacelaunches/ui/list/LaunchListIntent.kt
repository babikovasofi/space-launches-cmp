package ru.omgtu.babikova.spacelaunches.ui.list

sealed interface LaunchListIntent {
    data class CardClicked(val id: String) : LaunchListIntent
}
