package ru.omgtu.babikova.spacelaunches.detail

sealed interface LaunchDetailIntent {
    data class ProviderClicked(val id: Int) : LaunchDetailIntent
    data class PadClicked(val id: Int) : LaunchDetailIntent
}
