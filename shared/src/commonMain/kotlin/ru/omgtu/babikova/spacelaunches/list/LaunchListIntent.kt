package ru.omgtu.babikova.spacelaunches.list

sealed interface LaunchListIntent {
    data class CardClicked(val id: String) : LaunchListIntent
}
