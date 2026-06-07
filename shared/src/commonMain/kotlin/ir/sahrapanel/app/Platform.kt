package ir.sahrapanel.app

interface Platform {
    val name: String
    val version: String

    val type : PlatformType
}

expect fun getPlatform(): Platform


enum class PlatformType{
    ANDROID,
    JVM,
    WASM
}