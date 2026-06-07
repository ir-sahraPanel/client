package ir.sahrapanel.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import ir.sahrapanel.app.features.splash.SplashRoute
import ir.sahrapanel.app.features.splash.splashEntry


import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AppRoutes(){
    val config = routesConfigure
    val backStack = rememberNavBackStack(config, SplashRoute)
    NavDisplay(
        backStack =backStack,
        entryProvider = entryProvider {
            splashEntry()
        }

    )
}


private val routesConfigure = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(SplashRoute::class, SplashRoute.serializer())
        }
    }
}