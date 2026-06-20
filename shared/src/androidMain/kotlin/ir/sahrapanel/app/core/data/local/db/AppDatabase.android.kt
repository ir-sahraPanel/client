package ir.sahrapanel.app.core.data.local.db

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.mp.KoinPlatformTools


actual val roomDriver: SQLiteDriver
  get() = BundledSQLiteDriver()

actual val databaseBuilder: RoomDatabase.Builder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>
  get() {
  val context: Context = KoinPlatformTools.defaultContext().get().get()
  val dbFile = context.getDatabasePath("sahra_panel.db")
  return Room.databaseBuilder<ir.sahrapanel.app.core.data.dataSource.local.db.AppDatabase>(
    context = context,
    name = dbFile.absolutePath
  )
}

actual suspend fun SQLiteConnection.runSql(string: String) {
  this.prepare(string).use { statement ->
    statement.step()
  }
}