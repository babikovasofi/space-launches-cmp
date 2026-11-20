package ru.omgtu.babikova.spacelaunches.domain

data class LaunchPage(val items: List<Launch>, val nextOffset: Int?)

interface LaunchRepository {
    suspend fun getLaunches(query: String, offset: Int): LaunchPage

    suspend fun getLaunch(id: String): Launch?

    suspend fun getAgency(id: Int): Agency?

    suspend fun getPad(id: Int): Pad?
}
