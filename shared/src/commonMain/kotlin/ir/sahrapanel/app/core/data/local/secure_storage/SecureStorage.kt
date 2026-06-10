package ir.sahrapanel.app.core.data.local.secure_storage
interface SecureStorage {
    suspend fun saveString(key: SecureStorageKey, value: String)
    suspend fun getString(key: SecureStorageKey): String?
    suspend fun delete(key: SecureStorageKey)
    suspend fun clear()
}
enum class SecureStorageKey(val key: String) {
    USER_TOKEN("user_token")
}