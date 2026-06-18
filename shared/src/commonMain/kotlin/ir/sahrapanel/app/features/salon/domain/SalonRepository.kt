package ir.sahrapanel.app.features.salon.domain

import ir.sahrapanel.app.features.salon.data.CreateSalonRequest
import ir.sahrapanel.app.shared.Res

interface SalonRepository {
    suspend fun create(createSalonRequest: CreateSalonRequest) : Result<Unit>
    suspend fun get()

}