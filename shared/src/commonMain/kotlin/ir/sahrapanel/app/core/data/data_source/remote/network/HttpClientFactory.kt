package ir.sahrapanel.app.core.data.data_source.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.AttributeKey
import ir.sahrapanel.app.core.data.data_source.local.db.dao.UserTokenDao
import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType
import ir.sahrapanel.app.shared.BuildKonfig
import kotlinx.serialization.json.Json

fun createKtorClient(
    userTokenDao: UserTokenDao,
    platform: Platform,
    json: Json
) : HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }

    install(Logging) {
        level = LogLevel.ALL
        // 1. Choose a Logger (DEFAULT uses SLF4J on JVM; use SIMPLE for Native/Console)
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.withTag("HTTP Client").i { message }
            }
        }


        // 4. Optional: Sanitize sensitive headers to prevent leaks
        sanitizeHeader { header ->
            header == HttpHeaders.Authorization
        }

    }

    install(HttpCookies)
    expectSuccess = true
    val baseUrl = BuildKonfig.BASE_URL.trim()
    defaultRequest {

        url(baseUrl)
        contentType(ContentType.Application.Json)

        if (platform.type == PlatformType.WASM) {
            attributes.put(AttributeKey("withCredentials"), true)
        }
    }

    // فراخوانی اکستنشن جدا شده برای احراز هویت
    installAuthInterceptor(userTokenDao, platform, baseUrl)
}