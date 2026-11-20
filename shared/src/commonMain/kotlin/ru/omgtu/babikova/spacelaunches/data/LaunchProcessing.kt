package ru.omgtu.babikova.spacelaunches.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

private val launchJson = Json { ignoreUnknownKeys = true }

internal expect suspend fun processInWorker(operation: String, body: String): List<String>?

internal suspend fun decodeLaunchPage(body: String): LaunchPageDto = withContext(Dispatchers.Default) {
    val parts = processInWorker("page", body)
    if (parts == null) {
        launchJson.decodeFromString<LaunchPageDto>(body)
    } else {
        LaunchPageDto(
            next = parts.first().ifEmpty { null },
            results = (1 until parts.size).map { launchJson.decodeFromString<LaunchDto>(parts[it]) },
        )
    }
}

internal suspend fun <T> decodeRecord(operation: String, serializer: KSerializer<T>, body: String): T =
    withContext(Dispatchers.Default) {
        val compactBody = processInWorker(operation, body)?.single() ?: body
        launchJson.decodeFromString(serializer, compactBody)
    }

internal suspend fun <T> mergePage(
    previous: List<T>,
    page: List<T>,
    id: (T) -> String,
): List<T> = withContext(Dispatchers.Default) {
    val ids = (previous.asSequence() + page.asSequence()).joinToString(",", transform = id)
    val order = processInWorker("merge", ids)
    if (order == null) {
        (previous + page).distinctBy(id)
    } else {
        object : AbstractList<T>() {
            override val size = order.size

            override fun get(index: Int): T {
                val position = order[index].toInt()
                return if (position < previous.size) previous[position] else page[position - previous.size]
            }
        }
    }
}
