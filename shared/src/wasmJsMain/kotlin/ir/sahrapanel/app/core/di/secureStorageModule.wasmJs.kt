package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage
import org.koin.core.module.Module
import org.koin.dsl.module

actual val secureStorageModule: Module
    get() = module {
        single<ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage>(){WasmSecureStorage()}
    }


class WasmSecureStorage :
    ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage {
    override suspend fun saveString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey, value: String) {
       
    }

    override suspend fun getString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey): String? {
       return null
    }

    override suspend fun delete(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey) {

    }

    override suspend fun clear() {

    }
}