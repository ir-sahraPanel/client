package ir.sahrapanel.app.core.data.local.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

actual val roomDriver: SQLiteDriver
    get() = WebWorkerSQLiteDriver(createSQLiteWorker())


@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("() => new Worker(new URL('sqlite-wasm-worker/worker.js', import.meta.url), { type: 'module' })")
private external fun createSQLiteWorker(): Worker


actual val databaseBuilder: RoomDatabase.Builder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>
    get() {
        return Room.databaseBuilder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>(name = "sahra_panel.db")
    }

actual suspend fun SQLiteConnection.runSql(string: String) {
    this.prepare(string).use { statement ->
        statement.step()
    }
}