@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.dataSource.local.db.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import ir.sahrapanel.app.core.data.dataSource.local.db.entity.SalonMembershipEntity
import ir.sahrapanel.app.core.domain.UserRole
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface SalonMembershipDao {

    /**
     * Saves all flattened salon membership records into the database.
     * Used right after a successful OTP confirmation or profile sync.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMemberships(memberships: List<SalonMembershipEntity>)

    /**
     * Inserts or updates a single specific membership combination.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMembership(membership: SalonMembershipEntity)

    /**
     * Retrieves a reactive stream of all roles assigned to the user within a specific salon.
     */
    @Query("SELECT role FROM salon_memberships WHERE salonId = :salonId")
    fun getRolesForSalonFlow(salonId: Uuid): Flow<List<UserRole>>

    /**
     * Fetches all roles assigned to a specific salon as a one-shot database query.
     */
    @Query("SELECT role FROM salon_memberships WHERE salonId = :salonId")
    suspend fun getRolesForSalon(salonId: Uuid): List<UserRole>

    /**
     * Retrieves all saved membership assignments across all salons.
     */
    @Query("SELECT * FROM salon_memberships")
    fun getAllMembershipsFlow(): Flow<List<SalonMembershipEntity>>

    /**
     * Deletes all roles associated with a specific salon.
     */
    @Query("DELETE FROM salon_memberships WHERE salonId = :salonId")
    suspend fun deleteMembershipsForSalon(salonId: Uuid)

    /**
     * Clears all membership data. Extremely helpful during a user Logout sequence.
     */
    @Query("DELETE FROM salon_memberships")
    suspend fun clearAllMemberships()

    /**
     * Internal query helper to completely reset the active flag across every row.
     */
    @Query("UPDATE salon_memberships SET isDefault = 0")
    suspend fun clearAllDefaults()

    /**
     * Internal query helper to flag a single specific role within a specific salon as active.
     */
    @Query("UPDATE salon_memberships SET isDefault = 1 WHERE salonId = :salonId AND role = :role")
    suspend fun setRoleAsDefault(salonId: Uuid, role: UserRole)

    /**
     * Listens to the single active role assigned as the global application default.
     * If no record has isDefault = 1, it safely falls back to UserRole.UNKNOWN at the SQLite level.
     */
    @Query(
        """
        SELECT COALESCE(
            (SELECT role FROM salon_memberships WHERE isDefault = 1 LIMIT 1), 
            'UNKNOWN'
        )
    """
    )
    fun getDefaultRoleFlow(): Flow<UserRole>

    /**
     * An atomic database transaction that updates the user's active context.
     * It safely turns off all defaults before setting the single target role in the target salon.
     */
    @Transaction
    suspend fun updateActiveSalonAndRole(salonId: Uuid, role: UserRole) {
        clearAllDefaults()
        setRoleAsDefault(salonId, role)
    }

    /**
     * Fully drops and overrides all local memberships with fresh server data.
     * Automatically repairs the default flag if the incoming list contains a single marked record.
     */
    @Transaction
    suspend fun syncMemberships(newMemberships: List<SalonMembershipEntity>) {
        clearAllMemberships()
        insertAllMemberships(newMemberships)
    }
    /**
     * Listens to the single active/default role within a SPECIFIC salon.
     * If no role is explicitly marked as default for this salon, it falls back to 'UNKNOWN'.
     */
    @Query("""
        SELECT COALESCE(
            (SELECT role FROM salon_memberships WHERE salonId = :salonId AND isDefault = 1 LIMIT 1), 
            'UNKNOWN'
        )
    """)
    fun getDefaultRoleForSalonFlow(salonId: Uuid): Flow<UserRole>

    /**
     * Internal helper to turn off the default flag for all roles WITHIN a specific salon.
     */
    @Query("UPDATE salon_memberships SET isDefault = 0 WHERE salonId = :salonId")
    suspend fun clearDefaultRoleForSalon(salonId: Uuid)

    /**
     * Internal helper to mark a specific role as default WITHIN a specific salon.
     */
    @Query("UPDATE salon_memberships SET isDefault = 1 WHERE salonId = :salonId AND role = :role")
    suspend fun setRoleAsDefaultInSalon(salonId: Uuid, role: UserRole)

    /**
     * Atomic transaction to switch the active role inside a specific salon.
     * Example: Changes user's active view from ADMIN to SECRETARY inside Salon A.
     */
    @Transaction
    suspend fun updateDefaultRoleForSalon(salonId: Uuid, role: UserRole) {
        clearDefaultRoleForSalon(salonId)
        setRoleAsDefaultInSalon(salonId, role)
    }
}