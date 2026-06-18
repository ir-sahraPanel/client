package ir.sahrapanel.app.features.salon.data

import ir.sahrapanel.app.core.data.local.db.dao.SalonDao
import ir.sahrapanel.app.features.salon.domain.SalonRepository

class SalonRepositoryImpl(
    private val salonDataSource: SalonDataSource,
    private val salonDao: SalonDao
) : SalonRepository {
    override suspend fun create(createSalonRequest: CreateSalonRequest): Result<Unit> {
        return salonDataSource.create(createSalonRequest).fold(
            onSuccess = {
                // You can optionally save to a local cache database here if needed!
                Result.success(Unit)
            },
            onFailure = { exception ->
                // Passes the exact error (with your server's Persian message) up to the ViewModel
                Result.failure(exception)
            }
        )
    }
    override suspend fun get() {
        // salonDao
    }
}