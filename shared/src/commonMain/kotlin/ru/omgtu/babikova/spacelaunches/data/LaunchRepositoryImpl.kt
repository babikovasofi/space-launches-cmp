package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ru.omgtu.babikova.spacelaunches.domain.LaunchPage
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.resources.Res

private const val PAGE_SIZE = 20

internal class LaunchRepositoryImpl(private val client: HttpClient) : LaunchRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getLaunches(query: String, offset: Int): LaunchPage {
        val body = client.get(apiUrl("launch/")) {
            parameter("limit", PAGE_SIZE)
            parameter("offset", offset)
            if (query.isNotBlank()) parameter("search", query.trim())
        }.bodyAsText()

        return withContext(Dispatchers.Default) {
            val page = json.decodeFromString<LaunchPageDto>(body)
            val nextOffset = if (page.next != null && page.results.isNotEmpty()) {
                offset + page.results.size
            } else {
                null
            }
            LaunchPage(page.results.map(LaunchDto::toDomain), nextOffset)
        }
    }

    override suspend fun getLaunch(id: String) =
        getRecord<LaunchDto, _>("launch/$id/", LaunchDto::toDomain)

    override suspend fun getAgency(id: Int) =
        getRecord<AgencyDto, _>("agencies/$id/", AgencyDto::toDomain)

    override suspend fun getPad(id: Int) =
        getRecord<PadDto, _>("pad/$id/", PadDto::toDomain)

    private suspend inline fun <reified T, R> getRecord(path: String, crossinline map: (T) -> R): R? {
        val body = try {
            client.get(apiUrl(path)).bodyAsText()
        } catch (exception: ClientRequestException) {
            if (exception.response.status == HttpStatusCode.NotFound) return null
            throw exception
        }

        return withContext(Dispatchers.Default) {
            map(json.decodeFromString<T>(body))
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun apiUrl(path: String): String {
        val baseUrl = Res.readBytes("files/launch-api-url.txt").decodeToString().trim().trimEnd('/')
        check(baseUrl.startsWith("https://"))
        return "$baseUrl/$path"
    }
}
