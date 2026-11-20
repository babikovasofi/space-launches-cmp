package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout

internal expect fun createPlatformClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

internal fun createApiClient(): HttpClient = createPlatformClient {
    expectSuccess = true
    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
    }
}
