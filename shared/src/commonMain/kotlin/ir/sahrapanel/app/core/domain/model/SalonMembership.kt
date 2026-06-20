@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.domain.model

import ir.sahrapanel.app.core.domain.UserRole
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class SalonMembership(
  val salonId: Uuid,
  val roles: List<UserRole>
)
