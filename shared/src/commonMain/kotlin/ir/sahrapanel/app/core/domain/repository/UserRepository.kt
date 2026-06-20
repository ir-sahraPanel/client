package ir.sahrapanel.app.core.domain.repository

import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.features.auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface UserRepository {

    @OptIn(ExperimentalUuidApi::class)
    fun allMembers(salonId: Uuid): Flow<List<UserRole>>
    @OptIn(ExperimentalUuidApi::class)
    fun defaultRole(salonId: Uuid) : Flow<UserRole>
    @OptIn(ExperimentalUuidApi::class)
    suspend fun setDefaultRole(role: UserRole,
                               salonId: Uuid)
}