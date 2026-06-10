package ir.sahrapanel.app.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.sahrapanel.app.core.AppUiError
import ir.sahrapanel.app.core.data.remote.toTranslatableText
import ir.sahrapanel.app.core.domain.isPhoneNumber
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.enter_verification_code
import ir.sahrapanel.app.shared.error_invalid_phone
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    companion object {
        const val RESEND_TIMER = 59
    }

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    private val _effect = Channel<AuthEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()
    private var timerJob: Job? = null


    fun onEvent(event: AuthEvent) = when (event) {
        is AuthEvent.PhoneChanged -> _state.update { it.copy(phoneNumber = event.value) }
        is AuthEvent.OtpChanged -> _state.update { it.copy(enteredOtpCode = event.value) }
        AuthEvent.RequestOtp -> requestOtp()
        AuthEvent.ConfirmOtp -> confirmOtp()

        AuthEvent.ResendOtp -> {
            if (_state.value.timerValue == 0) {
                // کدهای مربوط به درخواست مجدد به سرور ...
                requestOtp()
                startTimer(RESEND_TIMER)
            } else {

            }
        }

        AuthEvent.ClearError -> _state.update { it.copy(error = null) }
        AuthEvent.ChangePhoneNumber -> viewModelScope.launch { _effect.send(AuthEffect.NavigateToPhoneEntry) }
        AuthEvent.StartResendTimer -> {
            if (_state.value.timerValue == 0) {
                // کدهای مربوط به درخواست مجدد به سرور ...
                startTimer(RESEND_TIMER)
            } else {

            }
        }
    }

    private fun startTimer(durationInSeconds: Int) {
        // اگر تایمر قبلی هنوز فعال است آن را لغو کن
        timerJob?.cancel()

        _state.update { it.copy(timerValue = durationInSeconds) }

        timerJob = viewModelScope.launch {
            while (_state.value.timerValue > 0) {
                delay(1000L)
                _state.update { it.copy(timerValue = it.timerValue - 1) }
            }
        }
    }

    private fun requestOtp() = viewModelScope.launch {
        val currentPhone = _state.value.phoneNumber

        // 1. Local validation (Synchronous)
        if (!currentPhone.isPhoneNumber()) {
            _state.update {
                it.copy(
                    error = AppUiError.FieldError(
                        AuthErrorTarget.PhoneNumber,
                        message = TranslatableText.Resource(Res.string.error_invalid_phone)
                    )
                )
            }
            return@launch
        }

        // 2. Start Loading & Clear previous errors
        _state.update { it.copy(isLoading = true, error = null) }

        try {
            // 3. Await Network Response
            repository.generateOtp(currentPhone)
                .onSuccess {
                    _effect.send(AuthEffect.NavigateToConfirmOtp)
                }
                .onFailure { e ->
                    _state.update {
                        it.copy(
                            error = AppUiError.FieldError(
                                AuthErrorTarget.PhoneNumber,
                                e.toTranslatableText()
                            )
                        )
                    }
                }
        } finally {
            // 4. Always runs after the repository call finishes (Success or Failure)
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun confirmOtp() = viewModelScope.launch {
        val currentOtp = _state.value.enteredOtpCode
        val currentPhone = _state.value.phoneNumber

        // 1. Local validation (Synchronous)
        if (currentOtp.isBlank()) {
            _state.update {
                it.copy(
                    error = AppUiError.FieldError(
                        AuthErrorTarget.OtpCode,
                        TranslatableText.Resource(Res.string.enter_verification_code)
                    )
                )
            }
            return@launch
        }

        // 2. Start Loading & Clear previous errors
        _state.update { it.copy(isLoading = true, error = null) }

        try {
            // 3. Await Network Response
            repository.confirmOtp(currentPhone, currentOtp)
                .onSuccess {
                    _effect.send(AuthEffect.NavigateToHome)
                }
                .onFailure { e ->
                    _state.update {
                        it.copy(
                            error = AppUiError.FieldError(
                                AuthErrorTarget.OtpCode,
                                e.toTranslatableText()
                            )
                        )
                    }
                }
        } finally {
            // 4. Always runs after the repository call finishes (Success or Failure)
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // جلوگیری از کارهای اضافه در پس‌زمینه در صورت بسته شدن صفحه
    }
}

