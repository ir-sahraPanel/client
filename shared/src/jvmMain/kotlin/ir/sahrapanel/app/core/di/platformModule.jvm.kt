package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType
import ir.sahrapanel.app.shared.BuildKonfig
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create

actual val platformModule: Module
    get() = module {
        single<Platform> { create<Platform>(::JVMPlatform) }
    }


private class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val version: String
        get() =  BuildKonfig.APP_VERSION
    override val type: PlatformType
        get() = PlatformType.JVM
}
