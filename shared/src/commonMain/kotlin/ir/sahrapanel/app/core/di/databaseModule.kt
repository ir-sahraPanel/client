package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.data.data_source.local.db.AppDatabase
import ir.sahrapanel.app.core.data.data_source.local.db.appDatabaseMigrations
import ir.sahrapanel.app.core.data.data_source.local.db.dao.LocationDao
import ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonDao
import ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonMembershipDao
import ir.sahrapanel.app.core.data.data_source.local.db.dao.UserTokenDao
import ir.sahrapanel.app.core.data.data_source.local.db.databaseBuilder
import ir.sahrapanel.app.core.data.data_source.local.db.roomDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create

fun provideAppDatabase(): AppDatabase =
    databaseBuilder.addMigrations(*appDatabaseMigrations).setDriver(
        roomDriver
    )
        .setQueryCoroutineContext(Dispatchers.Default).build()

fun provideLocationDao(db: AppDatabase): LocationDao = db.locationDao()
fun provideSalonDao(db: AppDatabase): SalonDao = db.salonDao()
fun provideUserTokenDao(db: AppDatabase): UserTokenDao = db.userTokenDao()
fun provideSalonMembershipDao(db: AppDatabase): SalonMembershipDao = db.salonMembershipDao()

val databaseModule: Module = module {
    single { create(::provideAppDatabase) }
    single { create(::provideLocationDao) }
    single { create(::provideSalonDao) }
    single { create(::provideUserTokenDao) }
    single { create(::provideSalonMembershipDao) }
}