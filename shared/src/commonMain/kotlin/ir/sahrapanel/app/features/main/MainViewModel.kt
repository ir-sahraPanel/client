package ir.sahrapanel.app.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.core.domain.repository.UserRepository
import ir.sahrapanel.app.core.domain.repository.SalonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalUuidApi::class)
class MainViewModel(
    private val userRepository: UserRepository, private val salonRepository: SalonRepository
) : ViewModel() {

    val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.setCurrentSalon -> _uiState.update { it.copy(currentSalon = event.value) }
        }
    }

    init {
        salonRepository.getAll()
            .onEach { salons ->
                if (_uiState.value.currentSalon == null && salons.isNotEmpty()) {
                    _uiState.update { it.copy(currentSalon = salons.first()) }
                }
            }
            .launchIn(viewModelScope)


        _uiState.map { it.currentSalon }
            .distinctUntilChanged() { old, new -> old?.id == new?.id }
            .flatMapLatest { currentSalon ->
                if (currentSalon != null) {
                    userRepository.defaultRole(currentSalon.id)
                } else {
                    flowOf(UserRole.UNKNOWN)
                }
            }.onEach { role ->
                _uiState.update { it.copy(currentRole = role) }
            }.launchIn(viewModelScope)

    }


}