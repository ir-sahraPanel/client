package ir.sahrapanel.app.features.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val phoneNumber: String,
    val name: String? = null,
    val isActive: Boolean = true,
)