package ir.sahrapanel.app.core.data.data_source.local.db.entity


import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "provinces")
data class ProvinceEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val slug: String,
    @ColumnInfo(name = "tel_prefix") val telPrefix: String?
)


