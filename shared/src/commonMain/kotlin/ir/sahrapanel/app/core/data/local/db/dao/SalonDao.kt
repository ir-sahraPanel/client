

package ir.sahrapanel.app.core.data.local.db.dao

import androidx.room3.Dao

import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Delete
import ir.sahrapanel.app.core.data.local.db.entity.SalonEntity
import kotlinx.coroutines.flow.Flow
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface SalonDao {

    // 1. Insert a single or multiple salons (e.g., syncing from network)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalon(salon: SalonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalons(salons: List<SalonEntity>)

    // 2. Fetch a specific salon by its Unique UUID
    @OptIn(ExperimentalUuidApi::class)
    @Query("SELECT * FROM salons WHERE id = :id LIMIT 1")
    suspend fun getSalonById(id: Uuid): SalonEntity?

    // 3. Observe all active salons (Reactive stream for UI lists)
    @Query("SELECT * FROM salons WHERE active = 1 ORDER BY name ASC")
    fun observeActiveSalons(): Flow<List<SalonEntity>>

    // 6. Update targeted fields dynamically without rewriting the entire entity
    @OptIn(ExperimentalUuidApi::class)
    @Query("UPDATE salons SET active = :isActive, updated_at = :updatedAt WHERE id = :id")
    suspend fun toggleSalonStatus(id: Uuid, isActive: Boolean, updatedAt: Instant)

    // 7. Delete a salon completely from the local cache
    @Delete
    suspend fun deleteSalon(salon: SalonEntity)
}