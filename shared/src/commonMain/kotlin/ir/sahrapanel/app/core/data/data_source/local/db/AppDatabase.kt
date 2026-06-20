@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.data_source.local.db

import androidx.room3.ColumnTypeConverters
import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteDriver
import ir.sahrapanel.app.core.data.data_source.local.db.entity.CityEntity
import ir.sahrapanel.app.core.data.data_source.local.db.entity.ProvinceEntity
import kotlin.uuid.ExperimentalUuidApi

@Database(
    entities = [
        ProvinceEntity::class,
        CityEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.SalonEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.EmployeeEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.SalonEmployeeEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.EmployeeServiceEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.EmployeeRentEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.ClientEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.ExtraServiceEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.ClientInvoiceEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.ClientInvoiceServiceItemEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.ClientInvoicePaymentEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.UserTokenEntity::class,
        _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.SalonMembershipEntity::class],
    version = 4,
    exportSchema = true
)
@ColumnTypeConverters(_root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.converters.RoomConverters::class)
@ConstructedBy(_root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): ir.sahrapanel.app.core.data.data_source.local.db.dao.LocationDao
    abstract fun salonDao(): ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonDao
    abstract fun userTokenDao(): ir.sahrapanel.app.core.data.data_source.local.db.dao.UserTokenDao
    abstract fun salonMembershipDao(): ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonMembershipDao
    /*   abstract fun employeeDao(): EmployeeDao
     abstract fun clientDao(): ClientDao
     abstract fun transactionDao(): TransactionDao*/
}

expect val roomDriver: SQLiteDriver
expect suspend fun SQLiteConnection.runSql(string: String)

expect val databaseBuilder : RoomDatabase.Builder<ir.sahrapanel.app.core.data.data_source.local.db.AppDatabase>


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<ir.sahrapanel.app.core.data.data_source.local.db.AppDatabase> {
    override fun initialize(): ir.sahrapanel.app.core.data.data_source.local.db.AppDatabase
}

