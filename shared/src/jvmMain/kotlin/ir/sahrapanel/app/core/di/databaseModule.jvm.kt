package ir.sahrapanel.app.core.di

import androidx.room3.Room
import ir.sahrapanel.app.core.data.local.db.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

import java.io.File

actual val databaseModule: Module
    get() = module {
        single<AppDatabase> { getDatabase() }
    }


private fun getDatabase() : AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
        .build()
}