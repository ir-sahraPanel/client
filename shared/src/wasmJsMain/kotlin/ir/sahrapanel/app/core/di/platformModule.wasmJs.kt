package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType
import ir.sahrapanel.app.shared.BuildKonfig
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create

actual val platformModule: Module
    get() = module {
        single<Platform> { create<Platform>(::WasmPlatform) }
    }

private class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val version: String
        get() = BuildKonfig.APP_VERSION
    override val type: PlatformType
        get() = PlatformType.WASM

}
