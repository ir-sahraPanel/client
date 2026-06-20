package ir.sahrapanel.app.features.create_salon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.sahrapanel.app.core.UiErrors
import ir.sahrapanel.app.core.data.data_source.remote.network.toTranslatableText
import ir.sahrapanel.app.core.location.domain.LocationRepository
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.features.create_salon.data.CreateSalonRequest
import ir.sahrapanel.app.features.create_salon.data.SalonOwnerRequest
import ir.sahrapanel.app.core.domain.repository.SalonRepository
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.validation_empty_city
import ir.sahrapanel.app.shared.validation_empty_owner_fname
import ir.sahrapanel.app.shared.validation_empty_owner_lname
import ir.sahrapanel.app.shared.validation_empty_province
import ir.sahrapanel.app.shared.validation_empty_salon_name
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CreateSalonViewModel(
    private val salonRepository: SalonRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateSalonUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<CreateSalonEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            locationRepository.getProvinces().onSuccess { provinces ->
                _uiState.update { it.copy(provinces = provinces) }
            }.onFailure {
                _effect.send(CreateSalonEffect.ShowErrorScreen(it.toTranslatableText()))
            }.also {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onEvent(event: CreateSalonEvent) {
        when (event) {
            is CreateSalonEvent.AddressChanged -> _uiState.update {
                it.copy(address = event.value)
            }

            is CreateSalonEvent.AdminFNameChange -> _uiState.update {
                it.copy(
                    adminFName = event.value,
                    errors = it.errors - CreateSalonErrorTarget.AdminFName
                )
            }

            is CreateSalonEvent.AdminLNameChange -> _uiState.update {
                it.copy(
                    adminLName = event.value,
                    errors = it.errors - CreateSalonErrorTarget.AdminLName
                )
            }

            is CreateSalonEvent.CityChanged -> _uiState.update {
                it.copy(
                    selectedCity = event.value,
                    isCityExpended = false,
                    errors = it.errors - CreateSalonErrorTarget.City
                )
            }

            is CreateSalonEvent.ProvinceChanged -> {
                _uiState.update {
                    it.copy(
                        selectedProvince = event.value,
                        isProvinceExpended = false,
                        errors = it.errors - CreateSalonErrorTarget.Province
                    )
                }
                getProvinceCities()
            }

            is CreateSalonEvent.SalonNameChanged -> _uiState.update {
                it.copy(
                    salonName = event.value,
                    errors = it.errors - CreateSalonErrorTarget.SalonName
                )
            }

            CreateSalonEvent.Submit -> onSubmitClick()
            is CreateSalonEvent.CityExpandedChange -> _uiState.update { it.copy(isCityExpended = event.value,
               ) }
            is CreateSalonEvent.ProvinceExpandedChange -> _uiState.update {
                it.copy(
                    isProvinceExpended = event.value
                )
            }
        }
    }

    private fun getProvinceCities() = viewModelScope.launch {
        val provinceId = _uiState.value.selectedProvince?.id ?: return@launch
        _uiState.update { it.copy(isLoading = true) }
        locationRepository.getCitiesForProvince(provinceId).onFailure { throwable ->
            _uiState.update {
                it.copy(
                    errors = mapOf(
                        CreateSalonErrorTarget.City to throwable.toTranslatableText()
                    )
                )
            }
        }.onSuccess { cities ->
            _uiState.update { it.copy(cities = cities) }
        }.also {
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun onSubmitClick() = viewModelScope.launch {
        val state = _uiState.value
        val errors = validate(state)
        // 1. Run local client validations before sending over the wire
        if (errors.isNotEmpty()) {
            _uiState.update { it.copy(errors = errors) }
            return@launch
        }

        // 2. Map state directly to the atomic DTO request payload
        val request = CreateSalonRequest(
            name = state.salonName,
            provinceId = state.selectedProvince!!.id, // Safely unboxed via validation checks above
            cityId = state.selectedCity!!.id,         // Safely unboxed via validation checks above
            logo = null,
            owner = SalonOwnerRequest(
                firstName = state.adminFName,
                lastName = state.adminLName,
            )
        )

        // 3. Set loading state before making the network call
        _uiState.update { it.copy(isLoading = true) }

        salonRepository.create(request).onFailure { throwable ->
            _effect.send(CreateSalonEffect.ShowErrorScreen(throwable.toTranslatableText()))
        }.onSuccess {
            _effect.send(CreateSalonEffect.ShowSuccessDialog)
            delay(2.seconds)
            _effect.send(CreateSalonEffect.NavToDashboardScreen)
        }.also {
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun validate(state: CreateSalonUiState): UiErrors<CreateSalonErrorTarget> {
        return buildMap {
            if (state.salonName.isBlank()) {
                put(
                    CreateSalonErrorTarget.SalonName,
                    TranslatableText.Resource(Res.string.validation_empty_salon_name)
                )
            }
            if (state.adminFName.isBlank()) {

                put(
                    CreateSalonErrorTarget.AdminFName,
                    TranslatableText.Resource(Res.string.validation_empty_owner_fname)
                )


            }
            if (state.adminLName.isBlank()) {
                put(
                    CreateSalonErrorTarget.AdminLName,
                    TranslatableText.Resource(Res.string.validation_empty_owner_lname)
                )
            }
            if (state.selectedCity == null) {
                put(
                    CreateSalonErrorTarget.City,
                    TranslatableText.Resource(Res.string.validation_empty_city)
                )
            }
            if (state.selectedProvince == null) {
                put(
                    CreateSalonErrorTarget.Province,
                    TranslatableText.Resource(Res.string.validation_empty_province)
                )

            }


        }
    }
}

