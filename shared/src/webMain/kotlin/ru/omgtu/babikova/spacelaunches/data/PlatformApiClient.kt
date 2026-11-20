package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin

internal actual fun createPlatformClient(config: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(Js) { config() }.also { client ->
        client.plugin(HttpSend).intercept { request ->
            try {
                execute(request)
            } catch (error: Error) {
                if (error.message != "Fail to fetch") throw error
                throw Exception("Network request failed", error)
            }
        }
    }
