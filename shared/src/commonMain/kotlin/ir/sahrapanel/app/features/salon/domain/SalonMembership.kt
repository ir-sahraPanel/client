@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.features.salon.domain

import ir.sahrapanel.app.core.domain.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class SalonMembership(
  val salonId: Uuid,
  val roles: List<UserRole>
)
