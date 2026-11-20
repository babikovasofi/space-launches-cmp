package ru.omgtu.babikova.spacelaunches.ui.list

import ru.omgtu.babikova.spacelaunches.ui.LoadState
import ru.omgtu.babikova.spacelaunches.ui.model.LaunchCardUi

data class LaunchListState(
    val query: String = "",
    val content: LoadState<LaunchListContent> = LoadState.Loading,
)

data class LaunchListContent(
    val items: List<LaunchCardUi>,
    val nextOffset: Int?,
    val nextPage: NextPage = NextPage.Idle,
)

enum class NextPage { Idle, Loading, Error }
