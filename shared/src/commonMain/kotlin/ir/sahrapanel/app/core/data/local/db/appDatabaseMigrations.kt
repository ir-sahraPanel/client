package ir.sahrapanel.app.core.data.local.db

import androidx.room3.migration.Migration
import androidx.sqlite.SQLiteConnection

val MIGRATION1_2 = Migration(1,2){
    it.runSql("""
            CREATE TABLE IF NOT EXISTS `user_tokens` (
                `id` TEXT NOT NULL, 
                `phoneNumber` TEXT NOT NULL, 
                `firstName` TEXT NOT NULL DEFAULT '', 
                `lastName` TEXT NOT NULL DEFAULT '', 
                `isActive` INTEGER NOT NULL DEFAULT 1, 
                `accessToken` TEXT NOT NULL, 
                `refreshToken` TEXT NOT NULL, 
                `accessTokenExpire` TEXT NOT NULL, 
                `refreshTokenExpire` TEXT NOT NULL, 
                PRIMARY KEY(`id`)
            )
            """.trimIndent()
    )
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override suspend fun migrate(connection: SQLiteConnection) {
        connection.runSql(
            "CREATE TABLE IF NOT EXISTS salons_new (" +
                    "id TEXT NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "logo TEXT, " +
                    "active INTEGER NOT NULL, " +
                    "province_id INTEGER, " +
                    "city_id INTEGER, " +
                    "created_at INTEGER, " +
                    "updated_at INTEGER, " +
                    "user_role TEXT NOT NULL, " +
                    "PRIMARY KEY(id))"
        )

        connection.runSql(
            "INSERT OR REPLACE INTO salons_new (id, name, logo, active, province_id, city_id, created_at, updated_at, user_role) " +
                    "SELECT id, name, logo, active, province_id, city_id, created_at, updated_at, user_role FROM salons"
        )

        connection.runSql("DROP TABLE IF EXISTS salons")
        connection.runSql("ALTER TABLE salons_new RENAME TO salons")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override suspend fun migrate(connection: SQLiteConnection) {

        // ۱. حذف ستون user_role از جدول salons
        // الف) ساخت جدول جدید سالن (بدون ستون user_role)
        connection.runSql("""
            CREATE TABLE IF NOT EXISTS `salons_new` (
                `id` TEXT NOT NULL, 
                `name` TEXT NOT NULL, 
                `logo` TEXT, 
                `active` INTEGER NOT NULL, 
                `province_id` INTEGER, 
                `city_id` INTEGER, 
                `created_at` INTEGER, 
                `updated_at` INTEGER, 
                PRIMARY KEY(`id`)
            )
        """.trimIndent())

        // ب) انتقال داده‌های قبلی به جدول جدید (INSERT OR REPLACE برای امنیت بیشتر)
        connection.runSql("""
            INSERT OR REPLACE INTO `salons_new` (`id`, `name`, `logo`, `active`, `province_id`, `city_id`, `created_at`, `updated_at`)
            SELECT `id`, `name`, `logo`, `active`, `province_id`, `city_id`, `created_at`, `updated_at` FROM `salons`
        """.trimIndent())

        // ج) حذف جدول قدیمی و تغییر نام جدول جدید به نام اصلی
        connection.runSql("DROP TABLE `salons`")
        connection.runSql("ALTER TABLE `salons_new` RENAME TO `salons`")

        // ----------------------------------------------------------------------

        // ۲. 💡 ساخت جدول جدید، مستقل و مجزا برای ممبرشیپ‌ها (به جای اضافه کردن ستون به توکن)
        connection.runSql("""
            CREATE TABLE IF NOT EXISTS `salon_memberships` (
                `salonId` TEXT NOT NULL, 
                `roles` TEXT NOT NULL, 
                PRIMARY KEY(`salonId`)
            )
        """.trimIndent())

        // 💡 نکته مهم: با این کار، جدول `user_tokens` شما در نسخه ۳ هیچ تغییری نمی‌کند،
        // دیتای سشن فعلی کاربران حفظ می‌شود و در زمان refreshToken هم به بن‌بست نمی‌خورید.
    }
}
val appDatabaseMigrations = arrayOf(
    MIGRATION1_2,MIGRATION_2_3,MIGRATION_3_4
)

