package ir.sahrapanel.app.core.di

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.sahrapanel.app.core.data.crypto.CryptoManager
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create


actual val secureStorageModule: Module
    get() = module {
        single<ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorage> { create(::AndroidSecureStorage)  }
    }





private val Context.dataStore by preferencesDataStore(name = "secure_prefs")

private class AndroidSecureStorage(private val context: Context) :
    ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorage {

    override suspend fun saveString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey, value: String) {
        val encrypted = CryptoManager.encrypt(value)
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key.key)] = encrypted
        }
    }

    override suspend fun getString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey): String? {
        val prefs = context.dataStore.data.firstOrNull() ?: return null
        val encrypted = prefs[stringPreferencesKey(key.key)] ?: return null
        return runCatching { CryptoManager.decrypt(encrypted) }.getOrNull()
    }

    override suspend fun delete(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey) {
        context.dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(key.key))
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
