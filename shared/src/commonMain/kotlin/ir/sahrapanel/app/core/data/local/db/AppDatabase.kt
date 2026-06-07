package ir.sahrapanel.app.core.data.local.db

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import androidx.room3.TypeConverters
import ir.sahrapanel.app.core.data.local.db.converters.RoomConverters
import ir.sahrapanel.app.core.data.local.db.entity.CityEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoicePaymentEntity
import ir.sahrapanel.app.core.data.local.db.entity.ClientInvoiceServiceItemEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeRentEntity
import ir.sahrapanel.app.core.data.local.db.entity.EmployeeServiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.ExtraServiceEntity
import ir.sahrapanel.app.core.data.local.db.entity.ProvinceEntity
import ir.sahrapanel.app.core.data.local.db.entity.SalonEmployeeEntity
import ir.sahrapanel.app.core.data.local.db.entity.SalonEntity

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
        ClientInvoicePaymentEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    /*    abstract fun locationDao(): LocationDao
        abstract fun salonDao(): SalonDao
        abstract fun employeeDao(): EmployeeDao
        abstract fun clientDao(): ClientDao
        abstract fun transactionDao(): TransactionDao*/
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
