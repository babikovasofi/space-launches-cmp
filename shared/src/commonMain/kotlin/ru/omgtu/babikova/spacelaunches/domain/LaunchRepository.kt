package ru.omgtu.babikova.spacelaunches.domain

interface LaunchRepository {

    suspend fun getLaunches(query: String = ""): List<Launch>

    suspend fun getLaunch(id: String): Launch

    suspend fun getAgency(id: Int): Agency

    suspend fun getPad(id: Int): Pad
}
