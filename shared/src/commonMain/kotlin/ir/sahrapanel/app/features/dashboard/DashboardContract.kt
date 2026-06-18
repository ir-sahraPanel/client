package ir.sahrapanel.app.features.dashboard

import androidx.compose.runtime.Immutable
import ir.sahrapanel.app.core.ErrorTarget
import ir.sahrapanel.app.core.UiErrors

sealed interface DashboardErrorTarget: ErrorTarget{

}


@Immutable
data class DashboardUiState(
    val errors: UiErrors<DashboardErrorTarget>
)


sealed interface DashboardEvent{

}

sealed interface DashboardEffect{

}