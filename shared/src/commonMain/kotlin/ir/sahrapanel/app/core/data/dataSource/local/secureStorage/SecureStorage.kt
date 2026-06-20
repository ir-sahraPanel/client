package ir.sahrapanel.app.core.data.dataSource.local.secureStorage
interface SecureStorage {
    suspend fun saveString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey, value: String)
    suspend fun getString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey): String?
    suspend fun delete(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey)
    suspend fun clear()
}
enum class SecureStorageKey(val key: String) {
    USER_TOKEN("user_token")
}