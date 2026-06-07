package ir.sahrapanel.app.core.data.remote

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.AttributeKey
import ir.sahrapanel.app.PlatformType
import ir.sahrapanel.app.core.data.local.secure_storage.SecureStorage
import ir.sahrapanel.app.core.data.local.secure_storage.SecureStorageKey
import ir.sahrapanel.app.features.auth.UserTokenDto
import ir.sahrapanel.app.getPlatform
import ir.sahrapanel.app.shared.BuildKonfig
import kotlinx.serialization.json.Json



fun createKtorClient(secureStorage: SecureStorage) = HttpClient {

    // 1. Install JSON negotiation
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }

    // 2. Logging for easier debugging during development
    install(Logging) {
        level = LogLevel.ALL
    }

    // 3. Crucial for WASM/Web: Enables browser cookie management
    install(HttpCookies)

    // 4. Default configuration for every single request
    defaultRequest {
        url(BuildKonfig.BASE_URL) // Replace with your base URL
        contentType(ContentType.Application.Json)

        // If it's WASM, tell the browser to attach HttpOnly cookies to the request
        if (getPlatform().type == PlatformType.WASM) {
            attributes.put(AttributeKey("withCredentials"), true)
        }
    }

    // 5. Intelligent Auth Plugin (Handles Header injection & Auto-Refresh)
    install(Auth) {
        bearer {
            // This loads the token before sending the request
            loadTokens {
                if (getPlatform().type == PlatformType.WASM) {
                    // On Web, browser handles cookies automatically. No headers needed.
                    null
                } else {
                    // On Android/Desktop, read from your SecureStorage
                    val accessToken = secureStorage.getString(SecureStorageKey.ACCESS_TOKEN)
                    val refreshToken = secureStorage.getString(SecureStorageKey.REFRESH_TOKEN)
                    if (accessToken != null && refreshToken != null) {
                        BearerTokens(accessToken, refreshToken)
                    } else null
                }
            }

            // Prevents sending Auth headers on public endpoints or when on Web
            sendWithoutRequest { request ->
                getPlatform().type == PlatformType.WASM || request.url.encodedPath.contains("auth/otp")
            }

            // This block triggers AUTOMATICALLY whenever the server returns a 401 Unauthorized
            refreshTokens {
                try {
                    // Re-instantiate a clean client to avoid infinite loops during refresh
                    val refreshClient = HttpClient {
                        install(ContentNegotiation) { json() }
                        install(HttpCookies)
                    }

                    val response = refreshClient.post("https://your-thesalonapp.ir/api/auth/token/refresh") {
                        contentType(ContentType.Application.Json)

                        if (getPlatform().type == PlatformType.WASM) {
                            // WASM sends an empty body, the browser automatically appends the HttpOnly "refresh_token" cookie
                            attributes.put(AttributeKey("withCredentials"), true)
                        } else {
                            // Android/Desktop explicitly pass the refresh token in the JSON body
                            val localRefreshToken = secureStorage.getString(SecureStorageKey.REFRESH_TOKEN) ?: ""
                           //todo setBody(RefreshTokenRequest(localRefreshToken))
                        }
                    }

                    if (response.status == HttpStatusCode.OK) {
                        // Ktor Server returns successResponse(UserTokenDto)
                        // Adjust parsing based on your generic successResponse wrapper structure
                        val baseResponse = response.body<UserTokenDto>()
                        val newTokens = baseResponse.token

                        if (getPlatform().type != PlatformType.WASM) {
                            // On Android/Desktop, update local persistent secure storage
                            secureStorage.saveString(SecureStorageKey.ACCESS_TOKEN, newTokens.accessToken)
                            secureStorage.saveString(SecureStorageKey.REFRESH_TOKEN, newTokens.refreshToken)
                        }

                        BearerTokens(
                            accessToken = newTokens.accessToken,
                            refreshToken = newTokens.refreshToken
                        )
                    } else {
                        // Refresh token expired or invalid -> Force logout in your state manager
                        null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }
}