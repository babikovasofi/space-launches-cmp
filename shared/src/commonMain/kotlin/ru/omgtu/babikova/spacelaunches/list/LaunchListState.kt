package ru.omgtu.babikova.spacelaunches.list

import ru.omgtu.babikova.spacelaunches.ui.model.LaunchCardUi

data class LaunchListState(
    val items: List<LaunchCardUi> = emptyList(),
)
