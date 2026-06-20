package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage
import ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.createDefinition
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.KeyStore
import java.security.MessageDigest
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

actual val secureStorageModule: Module
    get() = module {
        single<ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage> { create(::DesktopKeyStoreStorage) }
    }

private class DesktopKeyStoreStorage :
    ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorage {

    // PKCS12 is the standard, secure storage format for Java applications
    private val keyStore: KeyStore = KeyStore.getInstance("PKCS12")
    private val keyStoreFile: File

    // A system password used to access the keystore file.
    // Ideally, pass this via build environment variables or generate it securely.
    private val password = "ChangeThisToAConfigurablePassword123!".toCharArray()
    private val protectionParameter = KeyStore.PasswordProtection(password)

    init {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")

        val appDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "SahraPanel")
            os.contains("mac") -> File(userHome, "Library/Application Support/SahraPanel")
            else -> File(userHome, ".config/sahrapanel")
        }

        if (!appDir.exists()) appDir.mkdirs()
        keyStoreFile = File(appDir, "desktop_keystore.p12")

        if (keyStoreFile.exists()) {
            FileInputStream(keyStoreFile).use { fis ->
                keyStore.load(fis, password)
            }
        } else {
            // Initialize a brand new empty keystore
            keyStore.load(null, password)
            saveKeyStore()
        }
    }

    private fun saveKeyStore() {
        FileOutputStream(keyStoreFile).use { fos ->
            keyStore.store(fos, password)
        }
    }

    override suspend fun saveString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey, value: String) {
        // تبدیل رشته به یک هَش ۲۵۶ بیتی (۳۲ بایت) تا الگوریتم AES آن را به عنوان کلید قبول کند
        val sha256 = MessageDigest.getInstance("SHA-256")
        val keyBytes = sha256.digest(value.toByteArray(Charsets.UTF_8))

        // استفاده از AES به جای RAW
        val secretKey: SecretKey = SecretKeySpec(keyBytes, "AES")
        val entry = KeyStore.SecretKeyEntry(secretKey)

        // ذخیره‌سازی با پارامتر حفاظتی (protectionParameter) که از قبل تعریف کرده‌اید
        keyStore.setEntry(key.key, entry, protectionParameter)
        saveKeyStore()
    }
    override suspend fun getString(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey): String? {
        if (!keyStore.containsAlias(key.key)) return null

        val entry = keyStore.getEntry(key.key, protectionParameter) as? KeyStore.SecretKeyEntry
        val secretKey = entry?.secretKey ?: return null

        return String(secretKey.encoded, Charsets.UTF_8)
    }

    override suspend fun delete(key: ir.sahrapanel.app.core.data.data_source.local.secure_storage.SecureStorageKey) {
        if (keyStore.containsAlias(key.key)) {
            keyStore.deleteEntry(key.key)
            saveKeyStore()
        }
    }

    override suspend fun clear() {
        val aliases = keyStore.aliases().toList()
        for (alias in aliases) {
            keyStore.deleteEntry(alias)
        }
        saveKeyStore()
    }
}