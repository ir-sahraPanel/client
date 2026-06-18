package ir.sahrapanel.app.core.data.local.db

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.mp.KoinPlatformTools


actual val roomDriver: SQLiteDriver
  get() = BundledSQLiteDriver()

actual val databaseBuilder: RoomDatabase.Builder<AppDatabase>
  get() {
  val context: Context = KoinPlatformTools.defaultContext().get().get()
  val dbFile = context.getDatabasePath("sahra_panel.db")
  return Room.databaseBuilder<AppDatabase>(
    context = context,
    name = dbFile.absolutePath
  )
}

actual suspend fun SQLiteConnection.runSql(string: String) {
  this.prepare(string).use { statement ->
    statement.step()
  }
}