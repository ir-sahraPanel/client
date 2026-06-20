package ir.sahrapanel.app.core.domain.model

data class City(
    val id: Long,
    val name: String,
    val slug: String,
    val provinceId: Long,
)