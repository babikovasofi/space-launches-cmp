package ru.omgtu.babikova.spacelaunches.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun createPlatformClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(OkHttp) { config() }
