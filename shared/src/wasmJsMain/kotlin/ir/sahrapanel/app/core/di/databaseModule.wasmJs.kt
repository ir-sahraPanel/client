package ir.sahrapanel.app.core.di

import androidx.room3.Room
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import ir.sahrapanel.app.core.data.local.db.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import org.w3c.dom.Worker

actual val databaseModule: Module
    get() = module {
        single<AppDatabase> { getDatabase() }
    }

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("() => new Worker(new URL('sqlite-wasm-worker/worker.js', import.meta.url), { type: 'module' })")
private external fun createSQLiteWorker(): Worker

@OptIn(ExperimentalWasmJsInterop::class)
fun getDatabase(): AppDatabase {
    val driver = WebWorkerSQLiteDriver(createSQLiteWorker())
    return Room.databaseBuilder<AppDatabase>(name = "app.db")
        .setDriver(driver)
        .fallbackToDestructiveMigration(true)
        .build()
}