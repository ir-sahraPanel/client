package ir.sahrapanel.app.features.auth.presentation

import androidx.compose.runtime.Immutable
import ir.sahrapanel.app.core.ErrorTarget
import ir.sahrapanel.app.core.UiErrors
import ir.sahrapanel.app.core.ui.components.TranslatableText
sealed interface AuthErrorTarget: ErrorTarget{

   object PhoneNumber : AuthErrorTarget
   object OtpCode : AuthErrorTarget
}
@Immutable
data class AuthUiState(
    val phoneNumber: String = "",
    val enteredOtpCode: String = "",
    val isLoading: Boolean = false,
    val errors: UiErrors<AuthErrorTarget> = emptyMap(),
    val timerValue: Int = 0
)

sealed interface AuthEvent {
    data class PhoneChanged(val value: String) : AuthEvent
    data class OtpChanged(val value: String) : AuthEvent
    data object RequestOtp : AuthEvent
    data object ConfirmOtp : AuthEvent
    data object ResendOtp : AuthEvent
    data object ChangePhoneNumber: AuthEvent
    data object StartResendTimer : AuthEvent
}

sealed interface AuthEffect {
    data object NavigateToDashboard : AuthEffect
    data object NavigateToCreateSalon : AuthEffect
    data object NavigateToPhoneEntry : AuthEffect
    data object NavigateToConfirmOtp: AuthEffect
}