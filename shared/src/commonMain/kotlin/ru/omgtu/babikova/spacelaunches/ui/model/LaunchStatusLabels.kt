package ru.omgtu.babikova.spacelaunches.ui.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.status_failure
import ru.omgtu.babikova.spacelaunches.resources.status_go
import ru.omgtu.babikova.spacelaunches.resources.status_success
import ru.omgtu.babikova.spacelaunches.resources.status_tbc
import ru.omgtu.babikova.spacelaunches.resources.status_tbd
import ru.omgtu.babikova.spacelaunches.resources.status_unknown

private val statusLabels = mapOf(
    1 to Res.string.status_go,
    2 to Res.string.status_tbd,
    3 to Res.string.status_success,
    4 to Res.string.status_failure,
    8 to Res.string.status_tbc,
)

@Composable
fun launchStatusLabel(statusId: Int): String =
    stringResource(statusLabels[statusId] ?: Res.string.status_unknown)
