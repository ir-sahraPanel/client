package ir.sahrapanel.app.core.ui

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.core.ui.drawable.AngleUp
import ir.sahrapanel.app.core.ui.drawable.Gear
import ir.sahrapanel.app.core.ui.drawable.House
import ir.sahrapanel.app.core.ui.drawable.SahraPanelDrawable
import ir.sahrapanel.app.core.ui.drawable.User
import ir.sahrapanel.app.features.dashboard.DashboardRoute

sealed class MainNavItem(
    route: NavKey,
    title: TranslatableText,
    icon: ImageVector
) {
    data object OwnerDashboard : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.AngleUp
    )

    data object AdminDashboard : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.House
    )

    data object SecretaryDashboard : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.House
    )

    data object ArtistDashboard : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.House
    )

    data object Settings : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.Gear
    )

    data object Profile : MainNavItem(
        DashboardRoute,
        TranslatableText.DynamicString(""),
        SahraPanelDrawable.User
    )

    companion object {
        fun getNavigationItems(role: UserRole): List<MainNavItem> {
            return when (role) {
                UserRole.OWNER -> listOf(OwnerDashboard, Settings, Profile)
                UserRole.ADMIN -> listOf(AdminDashboard, Settings, Profile)
                UserRole.SECRETARY -> listOf(SecretaryDashboard, Settings, Profile)
                UserRole.ARTIST -> listOf(ArtistDashboard, Settings, Profile)
                UserRole.UNKNOWN -> listOf()
            }
        }
    }
}

