package ir.sahrapanel.app.features.auth.domain.model

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class User(
    val id: Uuid,
    val phoneNumber: String,
    val firstName: String = "",
    val lastName: String = "",
    val isActive: Boolean = true,
)