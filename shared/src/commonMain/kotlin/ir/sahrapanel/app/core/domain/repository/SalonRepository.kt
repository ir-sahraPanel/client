package ir.sahrapanel.app.core.domain.repository

import ir.sahrapanel.app.core.domain.model.Salon
import ir.sahrapanel.app.features.create_salon.data.CreateSalonRequest
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface SalonRepository {
    suspend fun create(createSalonRequest: CreateSalonRequest) : Result<Unit>
    @OptIn(ExperimentalUuidApi::class)
     fun get(salonId: Uuid): Flow<Salon>
     fun getAll(): Flow<List<Salon>>

}