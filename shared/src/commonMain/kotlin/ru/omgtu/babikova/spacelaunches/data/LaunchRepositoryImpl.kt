package ru.omgtu.babikova.spacelaunches.data

import ru.omgtu.babikova.spacelaunches.domain.Agency
import ru.omgtu.babikova.spacelaunches.domain.Launch
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.domain.Pad
import ru.omgtu.babikova.spacelaunches.domain.filterByName

class LaunchRepositoryImpl : LaunchRepository {

    override suspend fun getLaunches(query: String): List<Launch> = mockLaunches.filterByName(query)

    override suspend fun getLaunch(id: String): Launch = mockLaunches.first { it.id == id }

    override suspend fun getAgency(id: Int): Agency = mockAgencies.first { it.id == id }

    override suspend fun getPad(id: Int): Pad = mockPads.first { it.id == id }
}
