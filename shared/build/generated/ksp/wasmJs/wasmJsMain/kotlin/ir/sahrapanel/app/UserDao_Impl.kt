package ir.sahrapanel.app

import androidx.room3.EntityDeleteOrUpdateAdapter
import androidx.room3.EntityInsertAdapter
import androidx.room3.RoomDatabase
import androidx.room3.coroutines.createFlow
import androidx.room3.util.getColumnIndexOrThrow
import androidx.room3.util.performSuspending
import androidx.sqlite.SQLiteStatement
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUser: EntityInsertAdapter<User>

  private val __insertAdapterOfUser_1: EntityInsertAdapter<User>

  private val __deleteAdapterOfUser: EntityDeleteOrUpdateAdapter<User>

  private val __updateAdapterOfUser: EntityDeleteOrUpdateAdapter<User>
  init {
    this.__db = __db
    this.__insertAdapterOfUser = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `users` (`id`,`username`,`email`,`fullName`,`avatarUrl`,`isActive`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.username)
        statement.bindText(3, entity.email)
        val _tmpFullName: String? = entity.fullName
        if (_tmpFullName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpFullName)
        }
        val _tmpAvatarUrl: String? = entity.avatarUrl
        if (_tmpAvatarUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAvatarUrl)
        }
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__insertAdapterOfUser_1 = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `users` (`id`,`username`,`email`,`fullName`,`avatarUrl`,`isActive`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.username)
        statement.bindText(3, entity.email)
        val _tmpFullName: String? = entity.fullName
        if (_tmpFullName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpFullName)
        }
        val _tmpAvatarUrl: String? = entity.avatarUrl
        if (_tmpAvatarUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAvatarUrl)
        }
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String = "DELETE FROM `users` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `users` SET `id` = ?,`username` = ?,`email` = ?,`fullName` = ?,`avatarUrl` = ?,`isActive` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.username)
        statement.bindText(3, entity.email)
        val _tmpFullName: String? = entity.fullName
        if (_tmpFullName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpFullName)
        }
        val _tmpAvatarUrl: String? = entity.avatarUrl
        if (_tmpAvatarUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAvatarUrl)
        }
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.id)
      }
    }
  }

  public override suspend fun insert(user: User): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfUser.insertAndReturnId(_connection, user)
    _result
  }

  public override suspend fun insertAll(vararg users: User): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUser_1.insert(_connection, users)
  }

  public override suspend fun delete(user: User): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun update(user: User): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun getUserById(userId: Long): User? {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _result = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByEmail(email: String): User? {
    val _sql: String = "SELECT * FROM users WHERE email = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _result = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByUsername(username: String): User? {
    val _sql: String = "SELECT * FROM users WHERE username = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, username)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _result = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeUser(userId: Long): Flow<User?> {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _result = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAllUsers(): Flow<List<User>> {
    val _sql: String = "SELECT * FROM users ORDER BY username ASC"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActiveUsers(): List<User> {
    val _sql: String = "SELECT * FROM users WHERE isActive = 1 "
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM users"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActiveUserCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM users WHERE isActive = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchUsers(query: String): List<User> {
    val _sql: String = "SELECT * FROM users WHERE username LIKE '%' || ? || '%' OR email LIKE '%' || ? || '%'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFullName: String?
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          }
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = User(_tmpId,_tmpUsername,_tmpEmail,_tmpFullName,_tmpAvatarUrl,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(userId: Long) {
    val _sql: String = "DELETE FROM users WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
