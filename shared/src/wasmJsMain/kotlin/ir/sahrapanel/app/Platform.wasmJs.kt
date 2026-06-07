package ir.sahrapanel.app

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val version: String
        get() = "not impl"
    override val type: PlatformType
        get() = PlatformType.WASM

}

actual fun getPlatform(): Platform = WasmPlatform()