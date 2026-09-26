package ru.omgtu.babikova.spacelaunches.domain

data class Pad(
    val id: Int,
    val name: String,
    val latitude: String,
    val longitude: String,
    val location: PadLocation,
    val countryCode: String,
    val totalLaunchCount: Int,
)
