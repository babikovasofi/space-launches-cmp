package ru.omgtu.babikova.spacelaunches.domain

data class Agency(
    val id: Int,
    val name: String,
    val abbrev: String,
    val type: String,
    val countryCode: String,
    val foundingYear: Int?,
    val administrator: String?,
    val description: String,
    val totalLaunchCount: Int,
    val successfulLaunches: Int,
    val failedLaunches: Int,
    val pendingLaunches: Int,
)
