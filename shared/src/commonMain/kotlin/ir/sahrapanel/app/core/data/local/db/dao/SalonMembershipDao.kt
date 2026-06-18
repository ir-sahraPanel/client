package ir.sahrapanel.app.core.data.local.db.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import ir.sahrapanel.app.core.data.local.db.entity.SalonMembershipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SalonMembershipDao {

    @Query("SELECT * FROM salon_memberships")
    fun getAllMembershipsFlow(): Flow<List<SalonMembershipEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMemberships(memberships: List<SalonMembershipEntity>)

    @Query("DELETE FROM salon_memberships")
    suspend fun clearMemberships()
}