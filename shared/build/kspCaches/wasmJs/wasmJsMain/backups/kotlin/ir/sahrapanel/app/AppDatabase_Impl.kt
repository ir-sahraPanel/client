package ir.sahrapanel.app

import androidx.room3.InvalidationTracker
import androidx.room3.RoomOpenDelegate
import androidx.room3.migration.AutoMigrationSpec
import androidx.room3.migration.Migration
import androidx.room3.util.TableInfo
import androidx.room3.util.TableInfo.Companion.read
import androidx.room3.util.dropFtsSyncTriggers
import androidx.room3.util.performClear
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class AppDatabase_Impl : AppDatabase() {
  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "bc79323107cba9b482994b99d4e3622e", "aa5484c559c45c36d49376f9b66ea452") {
      public override suspend fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `fullName` TEXT, `avatarUrl` TEXT, `isActive` INTEGER NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_users_email` ON `users` (`email`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_users_username` ON `users` (`username`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bc79323107cba9b482994b99d4e3622e')")
      }

      public override suspend fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
      }

      public override suspend fun onCreate(connection: SQLiteConnection) {
      }

      public override suspend fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override suspend fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override suspend fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override suspend fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("username", TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("fullName", TableInfo.Column("fullName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("avatarUrl", TableInfo.Column("avatarUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesUsers.add(TableInfo.Index("index_users_email", true, listOf("email"), listOf("ASC")))
        _indicesUsers.add(TableInfo.Index("index_users_username", true, listOf("username"), listOf("ASC")))
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(ir.sahrapanel.app.User).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users")
  }

  public override suspend fun clearAllTables() {
    performClear(this, false, "users")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userDao(): UserDao = _userDao.value
}
