package ru.omgtu.babikova.spacelaunches.ui.list

import ru.omgtu.babikova.spacelaunches.ui.model.LaunchCardUi

data class LaunchListState(
    val query: String = "",
    val items: List<LaunchCardUi> = emptyList(),
)
