package ir.sahrapanel.app

import androidx.room3.RoomDatabaseConstructor

public actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
  actual override fun initialize(): AppDatabase = ir.sahrapanel.app.AppDatabase_Impl()
}
