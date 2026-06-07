package ir.sahrapanel.app.core.di

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.sahrapanel.app.core.data.crypto.CryptoManager
import ir.sahrapanel.app.core.data.local.secure_storage.SecureStorage
import ir.sahrapanel.app.core.data.local.secure_storage.SecureStorageKey
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.module.Module
import org.koin.dsl.module


actual val secureStorageModule: Module
    get() = module {
        single<SecureStorage> { AndroidSecureStorage(get()) }
    }





private val Context.dataStore by preferencesDataStore(name = "secure_prefs")

private class AndroidSecureStorage(private val context: Context) : SecureStorage {

    override suspend fun saveString(key: SecureStorageKey, value: String) {
        val encrypted = CryptoManager.encrypt(value)
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key.key)] = encrypted
        }
    }

    override suspend fun getString(key: SecureStorageKey): String? {
        val prefs = context.dataStore.data.firstOrNull() ?: return null
        val encrypted = prefs[stringPreferencesKey(key.key)] ?: return null
        return runCatching { CryptoManager.decrypt(encrypted) }.getOrNull()
    }

    override suspend fun delete(key: SecureStorageKey) {
        context.dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(key.key))
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
