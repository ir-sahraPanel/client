package ir.sahrapanel.app.core.location.data.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "cities",
    foreignKeys = [
        ForeignKey(
            entity = ProvinceEntity::class,
            parentColumns = ["id"],
            childColumns = ["province_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["province_id"])]
)
data class CityEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val slug: String,
    @ColumnInfo(name = "province_id") val provinceId: Long,
    @ColumnInfo(name = "county_id") val countyId: Long?
)