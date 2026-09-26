package ru.omgtu.babikova.spacelaunches.ui.model

import ru.omgtu.babikova.spacelaunches.domain.Launch

data class LaunchCardUi(
    val id: String,
    val name: String,
    val providerName: String,
    val launchTime: String,
    val statusId: Int,
    val statusName: String,
    val rocketConfigurationId: Int,
)

fun Launch.toCardUi() = LaunchCardUi(
    id = id,
    name = name,
    providerName = provider.name,
    launchTime = formatLaunchTime(net),
    statusId = status.id,
    statusName = status.name,
    rocketConfigurationId = rocket.id,
)
