package ru.omgtu.babikova.spacelaunches.ui

import kotlinx.coroutines.CancellationException

sealed interface LoadState<out T> {
    data object Loading : LoadState<Nothing>
    data object Empty : LoadState<Nothing>
    data object Error : LoadState<Nothing>
    data class Loaded<T>(val value: T) : LoadState<T>
}

internal suspend fun <T> loadState(load: suspend () -> T?): LoadState<T> =
    try {
        load()?.let { LoadState.Loaded(it) } ?: LoadState.Empty
    } catch (exception: CancellationException) {
        throw exception
    } catch (_: Exception) {
        LoadState.Error
    }
