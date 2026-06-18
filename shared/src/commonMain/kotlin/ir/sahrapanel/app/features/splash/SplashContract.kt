package ir.sahrapanel.app.features.splash

sealed interface SplashEvent {
    data object CheckUserIsLogin : SplashEvent

}
sealed interface SplashEffect {
    data object NavigateToDashboard : SplashEffect
    data object NavigateToAuth : SplashEffect
    data object NavigateToCreateSalon : SplashEffect
}