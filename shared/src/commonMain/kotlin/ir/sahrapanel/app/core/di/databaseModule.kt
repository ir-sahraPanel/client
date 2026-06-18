package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.data.local.db.AppDatabase
import ir.sahrapanel.app.core.data.local.db.appDatabaseMigrations
import ir.sahrapanel.app.core.data.local.db.dao.LocationDao
import ir.sahrapanel.app.core.data.local.db.dao.SalonDao
import ir.sahrapanel.app.core.data.local.db.dao.SalonMembershipDao
import ir.sahrapanel.app.core.data.local.db.dao.UserTokenDao
import ir.sahrapanel.app.core.data.local.db.databaseBuilder
import ir.sahrapanel.app.core.data.local.db.roomDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    single<AppDatabase>() {
        databaseBuilder
            .addMigrations(*appDatabaseMigrations)
            .setDriver(roomDriver)
            .setQueryCoroutineContext(Dispatchers.Default)
            .build()

    }

    single<LocationDao>() {
        get<AppDatabase>().locationDao()
    }
    single<SalonDao> { get<AppDatabase>().salonDao() }
    single<UserTokenDao>() {
        get<AppDatabase>().userTokenDao()

    }
    single<SalonMembershipDao>() {
        get<AppDatabase>().salonMembershipDao()

    }
}