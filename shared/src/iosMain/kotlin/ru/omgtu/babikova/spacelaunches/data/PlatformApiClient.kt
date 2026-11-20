package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

internal actual fun createPlatformClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Darwin) { config() }

internal actual suspend fun processInWorker(operation: String, body: String): List<String>? = null
