@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.local.db

import androidx.room3.ColumnTypeConverters
import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import androidx.room3.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import ir.sahrapanel.app.core.data.local.db.converters.RoomConverters
import ir.sahrapanel.app.core.data.local.db.dao.LocationDao
import ir.sahrapanel.app.core.data.local.db.dao.SalonDao
import ir.sahrapanel.app.core.data.local.db.dao.SalonMembershipDao
import ir.sahrapanel.app.core.data.local.db.dao.UserTokenDao
import ir.sahrapanel.app.core.data.local.db.entity.ClientEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoicePaymentEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoiceServiceItemEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeRentEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeServiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.ExtraServiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.SalonEmployeeEntity
import ir.sahrapanel.app.core.data.local.db.entity.SalonEntity
import ir.sahrapanel.app.core.data.local.db.entity.SalonMembershipEntity
import ir.sahrapanel.app.core.data.local.db.entity.UserTokenEntity
import ir.sahrapanel.app.core.location.data.model.CityEntity
import ir.sahrapanel.app.core.location.data.model.ProvinceEntity
import kotlin.uuid.ExperimentalUuidApi

@Database(
    entities = [
        ProvinceEntity::class,
        CityEntity::class,
        SalonEntity::class,
        EmployeeEntity::class,
        SalonEmployeeEntity::class,
        EmployeeServiceEntity::class,
        EmployeeRentEntity::class,
        ClientEntity::class,
        ExtraServiceEntity::class,
        ClientInvoiceEntity::class,
        ClientInvoiceServiceItemEntity::class,
        ClientInvoicePaymentEntity::class,
        UserTokenEntity::class,
        SalonMembershipEntity::class],
    version = 4,
    exportSchema = true
)
@ColumnTypeConverters(RoomConverters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun salonDao(): SalonDao
    abstract fun userTokenDao(): UserTokenDao
    abstract fun salonMembershipDao(): SalonMembershipDao
    /*   abstract fun employeeDao(): EmployeeDao
     abstract fun clientDao(): ClientDao
     abstract fun transactionDao(): TransactionDao*/
}

expect val roomDriver: SQLiteDriver
expect suspend fun SQLiteConnection.runSql(string: String)

expect val databaseBuilder : RoomDatabase.Builder<AppDatabase>


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

