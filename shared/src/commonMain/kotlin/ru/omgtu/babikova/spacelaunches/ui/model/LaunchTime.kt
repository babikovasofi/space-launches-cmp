package ru.omgtu.babikova.spacelaunches.ui.model

fun formatLaunchTime(isoDateTime: String): String {
    val dateParts = isoDateTime.substringBefore('T').split('-')
    if (dateParts.size != 3) return isoDateTime
    val (year, month, day) = dateParts
    val time = isoDateTime.substringAfter('T').take(5)
    return "$day.$month.$year $time UTC"
}
