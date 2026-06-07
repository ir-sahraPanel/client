package ir.sahrapanel.app

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import org.koin.core.context.GlobalContext

// 1. Define a global property to hold your context

class AndroidPlatform(private val appContext: Context) : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    // 2. Fill in the get() expression
    override val version: String
        get() = try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                appContext.packageManager.getPackageInfo(
                    appContext.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                appContext.packageManager.getPackageInfo(appContext.packageName, 0)
            }
            packageInfo.versionName ?: "1.0.0"
        } catch (e: Exception) {
            "Unknown"
        }

    override val type: PlatformType
        get() = PlatformType.ANDROID

}

actual fun getPlatform(): Platform {
    val context = GlobalContext.get().get<Context>()
    return AndroidPlatform(context)
}