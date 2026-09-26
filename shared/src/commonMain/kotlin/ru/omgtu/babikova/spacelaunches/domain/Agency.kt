package ru.omgtu.babikova.spacelaunches.domain

data class Agency(
    val id: Int,
    val name: String,
    val type: String,
    val countryCode: String,
    val foundingYear: Int?,
    val totalLaunchCount: Int,
)
