package ru.omgtu.babikova.spacelaunches

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform