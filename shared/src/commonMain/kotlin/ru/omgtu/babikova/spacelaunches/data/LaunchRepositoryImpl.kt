package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ru.omgtu.babikova.spacelaunches.domain.LaunchPage
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.resources.Res

private const val PAGE_SIZE = 20

internal class LaunchRepositoryImpl(private val client: HttpClient) : LaunchRepository {

    override suspend fun getLaunches(query: String, offset: Int): LaunchPage {
        val body = client.get(apiUrl("launch/")) {
            parameter("limit", PAGE_SIZE)
            parameter("offset", offset)
            if (query.isNotBlank()) parameter("search", query.trim())
        }.bodyAsText()

        val page = decodeLaunchPage(body)
        return withContext(Dispatchers.Default) {
            val nextOffset = if (page.next != null && page.results.isNotEmpty()) {
                offset + page.results.size
            } else {
                null
            }
            LaunchPage(page.results.map(LaunchDto::toDomain), nextOffset)
        }
    }

    override suspend fun getLaunch(id: String) =
        getRecord("launch/$id/", "launch", LaunchDto.serializer(), LaunchDto::toDomain)

    override suspend fun getAgency(id: Int) =
        getRecord("agencies/$id/", "agency", AgencyDto.serializer(), AgencyDto::toDomain)

    override suspend fun getPad(id: Int) =
        getRecord("pad/$id/", "pad", PadDto.serializer(), PadDto::toDomain)

    private suspend fun <T, R> getRecord(
        path: String,
        operation: String,
        serializer: KSerializer<T>,
        map: (T) -> R,
    ): R? {
        val body = try {
            client.get(apiUrl(path)).bodyAsText()
        } catch (exception: ClientRequestException) {
            if (exception.response.status == HttpStatusCode.NotFound) return null
            throw exception
        }

        return map(decodeRecord(operation, serializer, body))
    }

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun apiUrl(path: String): String {
        val baseUrl = Res.readBytes("files/launch-api-url.txt").decodeToString().trim().trimEnd('/')
        check(baseUrl.startsWith("https://"))
        return "$baseUrl/$path"
    }
}
