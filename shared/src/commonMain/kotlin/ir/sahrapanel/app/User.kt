package ir.sahrapanel.app

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import androidx.room3.Index


@Entity(
    tableName = "users",
    // Add indexes for frequently queried columns
    indices = [
        Index(value = ["email"], unique = true),
        Index(value = ["username"], unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val username: String,

    val email: String,

    val fullName: String? = null,

    val avatarUrl: String? = null,

    val isActive: Boolean = true,
)

// shared/src/commonMain/kotlin/dao/UserDao.kt



@Dao
interface UserDao {

    // Insert with conflict handling
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg users: User)

    // Update
    @Update
    suspend fun update(user: User)

    // Delete
    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Long)

    // Query single user
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    // Reactive queries with Flow
    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUser(userId: Long): Flow<User?>

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun observeAllUsers(): Flow<List<User>>

    // Complex queries
    @Query("SELECT * FROM users WHERE isActive = 1 ")
    suspend fun getActiveUsers(): List<User>

    // Aggregation queries
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")
    suspend fun getActiveUserCount(): Int

    @Query("SELECT * FROM users WHERE username LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%'")
    suspend fun searchUsers(query: String): List<User>


}