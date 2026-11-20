package ru.omgtu.babikova.spacelaunches.ui.list

sealed interface LaunchListIntent {
    data class QueryChanged(val value: String) : LaunchListIntent
    data object Retry : LaunchListIntent
    data object LoadNextPage : LaunchListIntent
    data class CardClicked(val id: String) : LaunchListIntent
}
