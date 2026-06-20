package ir.sahrapanel.app.core.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual val secureStorageModule: Module
    get() = module {
        single<ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorage>(){WasmSecureStorage()}
    }


class WasmSecureStorage :
    ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorage {
    override suspend fun saveString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey, value: String) {
       
    }

    override suspend fun getString(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey): String? {
       return null
    }

    override suspend fun delete(key: ir.sahrapanel.app.core.data.dataSource.local.secureStorage.SecureStorageKey) {

    }

    override suspend fun clear() {

    }
}