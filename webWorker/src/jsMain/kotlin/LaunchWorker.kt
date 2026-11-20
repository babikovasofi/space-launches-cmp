import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import ru.omgtu.babikova.spacelaunches.data.AgencyDto
import ru.omgtu.babikova.spacelaunches.data.LaunchDto
import ru.omgtu.babikova.spacelaunches.data.LaunchPageDto
import ru.omgtu.babikova.spacelaunches.data.PadDto

private val json = Json { ignoreUnknownKeys = true }
private val scope = js("self")

fun main() {
    scope.onmessage = { event: dynamic ->
        val operation = event.data.operation as String
        val body = event.data.body as String
        try {
            val result = when (operation) {
                "page" -> {
                    val page = json.decodeFromString<LaunchPageDto>(body)
                    arrayOf(page.next.orEmpty()) + page.results.map { json.encodeToString(it) }
                }
                "launch" -> normalize(LaunchDto.serializer(), body)
                "agency" -> normalize(AgencyDto.serializer(), body)
                "pad" -> normalize(PadDto.serializer(), body)
                "merge" -> {
                    val ids = body.split(',')
                    ids.indices.distinctBy { ids[it] }.map(Int::toString).toTypedArray()
                }
                else -> error("Unknown worker operation")
            }
            scope.postMessage(result)
        } catch (_: Exception) {
            scope.postMessage(null)
        }
    }
}

private fun <T> normalize(serializer: KSerializer<T>, body: String): Array<String> =
    arrayOf(json.encodeToString(serializer, json.decodeFromString(serializer, body)))
