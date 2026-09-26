package ru.omgtu.babikova.spacelaunches.ui.model

import ru.omgtu.babikova.spacelaunches.domain.Launch

data class LaunchDetailUi(
    val id: String,
    val name: String,
    val statusName: String,
    val statusDescription: String,
    val launchTime: String,
    val rocketFullName: String,
    val rocketConfigurationId: Int,
    val providerId: Int,
    val providerName: String,
    val padId: Int,
    val padName: String,
    val missionName: String?,
    val missionType: String?,
    val missionOrbit: String?,
    val missionDescription: String?,
)

fun Launch.toDetailUi() = LaunchDetailUi(
    id = id,
    name = name,
    statusName = status.name,
    statusDescription = status.description,
    launchTime = formatLaunchTime(net),
    rocketFullName = rocket.fullName,
    rocketConfigurationId = rocket.id,
    providerId = provider.id,
    providerName = provider.name,
    padId = pad.id,
    padName = pad.name,
    missionName = mission?.name,
    missionType = mission?.type,
    missionOrbit = mission?.orbit?.let { "${it.name} (${it.abbrev})" },
    missionDescription = mission?.description,
)
