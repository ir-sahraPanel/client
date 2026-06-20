package ir.sahrapanel.app.core.data.data_source.local.secure_storage

import ir.sahrapanel.app.features.auth.domain.model.UserToken
import kotlinx.serialization.json.Json

class UserStorage(
    private val secureStorage: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage,
    private val json: Json
) {
    suspend fun saveUserToken(userToken: UserToken) {
        val encoded = json.encodeToString(userToken)
        secureStorage.saveString(_root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey.USER_TOKEN, encoded)
    }

    suspend fun getUserToken(): UserToken? {
        val encoded = secureStorage.getString(_root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey.USER_TOKEN) ?: return null
        return runCatching { json.decodeFromString<UserToken>(encoded) }.getOrNull()
    }
    suspend fun getAccessToken(): String? = getUserToken()?.token?.accessToken

    suspend fun getRefreshToken(): String? = getUserToken()?.token?.refreshToken

    suspend fun isLoggedIn(): Boolean = getAccessToken() != null

    suspend fun clear() = secureStorage.delete(_root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey.USER_TOKEN)
}