package ir.sahrapanel.app.core.di

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType
import ir.sahrapanel.app.shared.BuildKonfig
import org.koin.core.context.GlobalContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create

actual val platformModule: Module
    get() = module {
       singleOf(::AndroidPlatform) bind Platform::class
    }

private class AndroidPlatform() : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    // 2. Fill in the get() expression
    override val version: String
        get() = BuildKonfig.APP_VERSION


    override val type: PlatformType
        get() = PlatformType.ANDROID

}
