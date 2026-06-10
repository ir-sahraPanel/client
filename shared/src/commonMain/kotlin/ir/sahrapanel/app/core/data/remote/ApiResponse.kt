package ir.sahrapanel.app.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<out T>(
    val status: Int,
    val message: String,
    val data: T?,
    val isSuccess: Boolean,
)

