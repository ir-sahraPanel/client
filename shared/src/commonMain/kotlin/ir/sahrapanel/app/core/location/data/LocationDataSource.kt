package ir.sahrapanel.app.core.location.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ir.sahrapanel.app.core.data.remote.safeApiCall
import ir.sahrapanel.app.core.location.data.model.CityDto
import ir.sahrapanel.app.core.location.data.model.ProvinceDto

class LocationDataSource(private val client: HttpClient) {

    // GET /locations/provinces
    suspend fun getProvinces(): Result<List<ProvinceDto>> = safeApiCall {
        client.get("locations/provinces").body()
    }

    // GET /locations/provinces/{provinceId}/cities
    suspend fun getCities(provinceId: Long): Result<List<CityDto>> = safeApiCall {
        client.get("locations/provinces/$provinceId/cities").body()
    }
}
