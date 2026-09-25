package ru.omgtu.babikova.spacelaunches.domain

data class Launch(
    val id: String,
    val name: String,
    val status: LaunchStatus,
    val net: String,
    val windowStart: String,
    val windowEnd: String,
    val probability: Int?,
    val provider: LaunchProvider,
    val rocket: RocketConfiguration,
    val mission: Mission?,
    val pad: LaunchPad,
    val imageUrl: String,
)

data class LaunchStatus(
    val id: Int,
    val name: String,
    val abbrev: String,
    val description: String,
)

data class LaunchProvider(
    val id: Int,
    val name: String,
    val type: String,
)

data class RocketConfiguration(
    val id: Int,
    val name: String,
    val fullName: String,
    val variant: String,
)

data class Mission(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val orbit: Orbit?,
)

data class Orbit(
    val name: String,
    val abbrev: String,
)

data class LaunchPad(
    val id: Int,
    val name: String,
    val latitude: String,
    val longitude: String,
    val location: PadLocation,
)

data class PadLocation(
    val id: Int,
    val name: String,
    val countryCode: String,
)

fun List<Launch>.filterByName(query: String): List<Launch> {
    val needle = query.trim()
    if (needle.isEmpty()) return this
    return filter { it.name.contains(needle, ignoreCase = true) }
}
