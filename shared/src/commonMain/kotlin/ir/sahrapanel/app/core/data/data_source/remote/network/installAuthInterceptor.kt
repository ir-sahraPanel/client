@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.data_source.remote.network

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.AttributeKey
import ir.sahrapanel.app.core.data.data_source.local.db.dao.UserTokenDao
import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType
import ir.sahrapanel.app.features.auth.data.dto.RefreshTokenRequest
import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto
import ir.sahrapanel.app.features.auth.data.dto.toEntity
import kotlin.uuid.ExperimentalUuidApi

fun HttpClientConfig<*>.installAuthInterceptor(
    userTokenDao: UserTokenDao,
    platform: Platform,
    baseUrl: String
) {

    install(Auth) {
        bearer {
            // ۱. بارگذاری توکن‌ها
            loadTokens {
                if (platform.type == PlatformType.WASM) {
                    null
                } else {
                    val userToken =  userTokenDao.getCurrentUserToken()
                    val accessToken =userToken?.accessToken
                    val refreshToken = userToken?.refreshToken
                    if (accessToken != null && refreshToken != null) {
                        BearerTokens(accessToken, refreshToken)
                    } else null
                }
            }

            // ۲. شرایط عدم ارسال هدر
            sendWithoutRequest { request ->
                platform.type == PlatformType.WASM || request.url.encodedPath.contains("auth/otp")
            }

            // ۳. منطق رفرش توکن در صورت بروز خطای 401
            refreshTokens {
                try {
                    val refreshClient = HttpClient {
                        install(ContentNegotiation) { json() }
                        install(HttpCookies)
                    }

                    val response = refreshClient.post("${baseUrl}api/auth/token/refresh") {
                        contentType(ContentType.Application.Json)

                        if (platform.type == PlatformType.WASM) {
                            attributes.put(AttributeKey("withCredentials"), true)
                        } else {
                            val localRefreshToken = userTokenDao.getCurrentUserToken()?.refreshToken ?: ""
                            setBody(RefreshTokenRequest(localRefreshToken))
                        }
                    }

                    if (response.status == HttpStatusCode.OK) {
                        val baseResponse = response.body<UserTokenDto>()

                        if (platform.type != PlatformType.WASM) {
                            userTokenDao.insertOrUpdateToken(baseResponse.toEntity())
                        }

                        BearerTokens(
                            accessToken = baseResponse.token.accessToken,
                            refreshToken = baseResponse.token.refreshToken
                        )
                    } else {
                        // TODO: در این بخش در صورت انقضای رفرش‌توکن، ایونت خروج کاربر (Logout) را به State Manager بفرستید
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