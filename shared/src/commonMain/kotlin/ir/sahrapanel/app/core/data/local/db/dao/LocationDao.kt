package ir.sahrapanel.app.core.data.local.db.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import ir.sahrapanel.app.core.location.data.model.CityEntity
import ir.sahrapanel.app.core.location.data.model.ProvinceEntity

@Dao
interface LocationDao {

    // --- Province Operations ---

    @Query("SELECT * FROM provinces ORDER BY name ASC")
    suspend fun getProvinces(): List<ProvinceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProvinces(provinces: List<ProvinceEntity>)

    @Query("DELETE FROM provinces")
    suspend fun clearAllProvinces()


    // --- City Operations ---

    @Query("SELECT * FROM cities WHERE province_id = :provinceId ORDER BY name ASC")
    suspend fun getCitiesForProvince(provinceId: Long): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Query("DELETE FROM cities WHERE province_id = :provinceId")
    suspend fun clearCitiesForProvince(provinceId: Long)
}