package ir.sahrapanel.app.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.sahrapanel.app.core.data.local.secure_storage.UserStorage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val userStorage: UserStorage) : ViewModel() {
    companion object{
      const val SPLASH_DELAY = 1000L
    }

    private val _effect = Channel<SplashEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: SplashEvent) = when (event) {
        is SplashEvent.CheckUserIsLogin -> checkUserIsLogin()
    }


    private fun checkUserIsLogin() = viewModelScope.launch {
        delay(SPLASH_DELAY)
        if (userStorage.isLoggedIn()) {
            _effect.send(SplashEffect.NavigateToHome)
        } else {
            _effect.send(SplashEffect.NavigateToAuth)
        }

    }

}