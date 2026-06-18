package ir.sahrapanel.app.core.location.domain

data class City(
    val id: Long,
    val name: String,
    val slug: String,
    val provinceId: Long,
)