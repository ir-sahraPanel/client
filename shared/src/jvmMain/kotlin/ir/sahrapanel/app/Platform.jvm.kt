package ir.sahrapanel.app

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val version: String
        get() = ""
    override val type: PlatformType
        get() = PlatformType.JVM
}

actual fun getPlatform(): Platform = JVMPlatform()