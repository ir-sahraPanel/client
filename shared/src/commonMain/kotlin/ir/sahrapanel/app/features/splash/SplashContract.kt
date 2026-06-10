package ir.sahrapanel.app.features.splash

sealed interface SplashEvent {
    data object CheckUserIsLogin : SplashEvent

}
sealed interface SplashEffect {
    data object NavigateToHome : SplashEffect
    data object NavigateToAuth : SplashEffect
}