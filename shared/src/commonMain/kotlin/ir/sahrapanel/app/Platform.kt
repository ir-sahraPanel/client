package ir.sahrapanel.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform