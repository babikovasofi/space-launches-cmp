package ru.omgtu.babikova.spacelaunches.ui.model

import ru.omgtu.babikova.spacelaunches.domain.Launch

data class LaunchDetailUi(
    val id: String,
    val name: String,
    val statusName: String,
    val statusDescription: String,
    val launchTime: String,
    val window: String,
    val probability: String?,
    val rocketFullName: String,
    val rocketConfigurationId: Int,
    val provider: ProviderUi,
    val pad: PadUi,
    val mission: MissionUi?,
)

data class ProviderUi(
    val id: Int,
    val name: String,
    val type: String,
)

data class PadUi(
    val id: Int,
    val name: String,
    val location: String,
)

data class MissionUi(
    val name: String,
    val type: String,
    val orbit: String?,
    val description: String,
)

fun Launch.toDetailUi() = LaunchDetailUi(
    id = id,
    name = name,
    statusName = status.name,
    statusDescription = status.description,
    launchTime = formatLaunchTime(net),
    window = "${formatLaunchTime(windowStart)} — ${formatLaunchTime(windowEnd)}",
    probability = probability?.let { "$it %" },
    rocketFullName = rocket.fullName,
    rocketConfigurationId = rocket.id,
    provider = ProviderUi(provider.id, provider.name, provider.type),
    pad = PadUi(pad.id, pad.name, pad.location.name),
    mission = mission?.let {
        MissionUi(
            name = it.name,
            type = it.type,
            orbit = it.orbit?.let { orbit -> "${orbit.name} (${orbit.abbrev})" },
            description = it.description,
        )
    },
)
