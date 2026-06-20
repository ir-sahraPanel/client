package ir.sahrapanel.app.core.data.data_source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import ir.sahrapanel.app.core.data.data_source.remote.network.safeApiCall
import ir.sahrapanel.app.features.create_salon.data.CreateSalonRequest

class SalonDataSource(val client: HttpClient) {
    suspend fun create(createSalonRequest: CreateSalonRequest): Result<Unit> =
        safeApiCall {
            client.post("admin/salons") {
                setBody(createSalonRequest)
            }.body()
        }
}