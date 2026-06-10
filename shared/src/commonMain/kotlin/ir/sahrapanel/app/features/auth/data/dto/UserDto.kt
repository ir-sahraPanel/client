package ir.sahrapanel.app.features.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val phoneNumber: String,
    val name: String? = null,
    val isActive: Boolean = true,
)