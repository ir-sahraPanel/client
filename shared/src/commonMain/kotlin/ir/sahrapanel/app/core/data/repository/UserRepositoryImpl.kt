@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.repository

import ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonMembershipDao
import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class UserRepositoryImpl(private val membershipDao: SalonMembershipDao) : UserRepository {
    override fun allMembers(salonId: Uuid): Flow<List<UserRole>> {
        return membershipDao.getRolesForSalonFlow(salonId)
    }

    override fun defaultRole(salonId: Uuid): Flow<UserRole> {
        return membershipDao.getDefaultRoleForSalonFlow(salonId)
    }

    override suspend fun setDefaultRole(
        role: UserRole,
        salonId: Uuid
    ) {
        membershipDao.setRoleAsDefault(salonId,role)
    }

}