package ir.sahrapanel.app.features.salon.presentation.create

import androidx.compose.runtime.Immutable
import ir.sahrapanel.app.core.ErrorTarget
import ir.sahrapanel.app.core.UiErrors
import ir.sahrapanel.app.core.location.domain.City
import ir.sahrapanel.app.core.location.domain.Province
import ir.sahrapanel.app.core.ui.components.TranslatableText

sealed interface CreateSalonErrorTarget : ErrorTarget {
    data object SalonName : CreateSalonErrorTarget
    data object Province : CreateSalonErrorTarget
    data object City : CreateSalonErrorTarget
    data object AdminFName : CreateSalonErrorTarget
    data object AdminLName : CreateSalonErrorTarget
}


@Immutable
data class CreateSalonUiState(
    val salonName: String = "",
    val address: String = "",
    val provinces: List<Province> = listOf(),
    val cities: List<City> = listOf(),
    val selectedProvince: Province? = null,
    val selectedCity: City? = null,
    val isProvinceExpended: Boolean = false,
    val isCityExpended: Boolean = false,
    val adminFName: String = "",
    val adminLName: String = "",
    val isLoading: Boolean = false,
    val errors: UiErrors<CreateSalonErrorTarget> = emptyMap()
)


sealed interface CreateSalonEvent {
    data class SalonNameChanged(val value: String) : CreateSalonEvent
    data class AddressChanged(val value: String) : CreateSalonEvent
    data class ProvinceChanged(val value: Province) : CreateSalonEvent
    data class CityChanged(val value: City) : CreateSalonEvent
    data class AdminFNameChange(val value: String) : CreateSalonEvent
    data class AdminLNameChange(val value: String) : CreateSalonEvent
    data class ProvinceExpandedChange(val value: Boolean):CreateSalonEvent
    data class CityExpandedChange(val value: Boolean):CreateSalonEvent
    data object Submit : CreateSalonEvent


}


sealed interface CreateSalonEffect {
    data class ShowErrorScreen(val message: TranslatableText) : CreateSalonEffect
    data object ShowSuccessDialog : CreateSalonEffect
    data object NavToDashboardScreen : CreateSalonEffect
}