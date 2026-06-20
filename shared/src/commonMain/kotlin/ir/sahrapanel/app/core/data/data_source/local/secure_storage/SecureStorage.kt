package ir.sahrapanel.app.core.data.data_source.local.secure_storage
interface SecureStorage {
    suspend fun saveString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey, value: String)
    suspend fun getString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey): String?
    suspend fun delete(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey)
    suspend fun clear()
}
enum class SecureStorageKey(val key: String) {
    USER_TOKEN("user_token")
}