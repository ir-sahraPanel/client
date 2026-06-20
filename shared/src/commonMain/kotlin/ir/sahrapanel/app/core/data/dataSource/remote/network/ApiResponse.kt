package ir.sahrapanel.app.core.data.dataSource.remote.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<out T>(
    val status: Int,
    val message: String,
    val data: T?,
    val isSuccess: Boolean,
)

