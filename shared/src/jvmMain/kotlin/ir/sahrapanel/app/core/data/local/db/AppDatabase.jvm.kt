package ir.sahrapanel.app.core.data.local.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

actual val roomDriver: SQLiteDriver
    get() = BundledSQLiteDriver()

actual val databaseBuilder: RoomDatabase.Builder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>
    get() {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "sahra_panel.db") //todo not in tmp
    return Room.databaseBuilder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>(
        name = dbFile.absolutePath,
    )
}
actual suspend fun SQLiteConnection.runSql(string: String) {
    this.prepare(string).use { statement ->
        statement.step()
    }
}