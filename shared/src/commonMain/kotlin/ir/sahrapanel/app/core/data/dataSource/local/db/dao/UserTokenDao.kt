@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.dataSource.local.db.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface UserTokenDao {

    // 1. Save or Update the session (e.g., on successful login or registration)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateToken(userToken: ir.sahrapanel.app.core.data.dataSource.local.db.entity.UserTokenEntity)

    // 2. Get the current user token as a Flow for real-time UI updates
    @Query("SELECT * FROM user_tokens LIMIT 1")
    fun observeCurrentUserToken(): Flow<ir.sahrapanel.app.core.data.dataSource.local.db.entity.UserTokenEntity?>

    // 3. One-shot fetch for the current user token (useful in Interceptors/Repositories)
    @Query("SELECT * FROM user_tokens LIMIT 1")
    suspend fun getCurrentUserToken(): ir.sahrapanel.app.core.data.dataSource.local.db.entity.UserTokenEntity?

    // 4. Update tokens during a token refresh operation
    @Query(
        """
        UPDATE user_tokens 
        SET accessToken = :newAccessToken, 
            refreshToken = :newRefreshToken, 
            accessTokenExpire = :newAccessExpire, 
            refreshTokenExpire = :newRefreshExpire 
        WHERE id = :id
    """
    )
    suspend fun updateTokens(
        id: Uuid,
        newAccessToken: String,
        newRefreshToken: String,
        newAccessExpire: Instant,
        newRefreshExpire: Instant
    )

    // 5. Update user profile data independently of tokens
    @Query(
        """
        UPDATE user_tokens 
        SET firstName = :firstName, 
            lastName = :lastName, 
            phoneNumber = :phoneNumber 
        WHERE id = :id
    """
    )
    suspend fun updateProfile(id: Uuid, firstName: String, lastName: String, phoneNumber: String)

    // 6. Delete the tokens / Clear session (e.g., on Logout or unauthorized error)
    @Query("DELETE FROM user_tokens")
    suspend fun clearSession()
}