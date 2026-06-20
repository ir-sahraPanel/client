package ir.sahrapanel.app.features.main

import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.core.ui.MainNavItem
import ir.sahrapanel.app.core.domain.model.Salon

data class MainUiState(
    val currentSalon: Salon? = null,
    val currentRole: UserRole = UserRole.UNKNOWN,
    val navItems: List<MainNavItem> = listOf()
)


sealed interface MainEvent{
   data class setCurrentSalon(val value: Salon):MainEvent
}