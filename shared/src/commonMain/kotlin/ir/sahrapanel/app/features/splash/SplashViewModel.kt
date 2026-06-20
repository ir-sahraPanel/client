package ir.sahrapanel.app.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonDao
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

class SplashViewModel(
    private val authRepository: AuthRepository,
    private val salonDao: ir.sahrapanel.app.core.data.data_source.local.db.dao.SalonDao
) : ViewModel() {

    companion object{
      const val SPLASH_DELAY = 1000L
    }

    private val _effect = Channel<SplashEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: SplashEvent) = when (event) {
        is SplashEvent.CheckUserIsLogin -> checkUserIsLogin()
    }


    private fun checkUserIsLogin() = viewModelScope.launch {
        if (!authRepository.isLoggedIn()) {
            _effect.send(SplashEffect.NavigateToAuth)
            return@launch
        }

        // Only query the DB if the user is actually logged in
        val userHasSalon = salonDao.observeActiveSalons().firstOrNull()

        val effect = if (userHasSalon != null) {
            SplashEffect.NavigateToDashboard
        } else {
            SplashEffect.NavigateToCreateSalon
        }

        _effect.send(effect)
    }

}