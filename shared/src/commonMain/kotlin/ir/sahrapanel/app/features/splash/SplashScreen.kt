package ir.sahrapanel.app.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.domain.platform.Platform
import ir.sahrapanel.app.core.domain.platform.PlatformType

import ir.sahrapanel.app.core.ui.theme.SahraPanelTheme
import ir.sahrapanel.app.shared.BuildKonfig

import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SplashRoute : NavKey


fun EntryProviderScope<NavKey>.splashEntry(
    navigateToAuth: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToCreateSalon:()-> Unit
) {
    entry<SplashRoute> {

        val platform = koinInject<Platform>()
        val viewModel = koinViewModel<SplashViewModel>()
        SplashScreen(platform)
        LaunchedEffect(Unit) {
            // Fire the event
            viewModel.onEvent(SplashEvent.CheckUserIsLogin)
        }
        LaunchedEffect(Unit) {
            viewModel.effect.collect {
                when (it) {
                    SplashEffect.NavigateToAuth -> navigateToAuth()
                    SplashEffect.NavigateToDashboard -> navigateToDashboard()
                    SplashEffect.NavigateToCreateSalon -> navigateToCreateSalon()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SplashScreen(platform : Platform) {
    Scaffold{paddingValues ->
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .padding(paddingValues).fillMaxSize()
            .padding(16.dp)
        ){
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("صحرا",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary)
                Text("برنامه مدیریت هوشمند سالن زیبایی",
                    color = MaterialTheme.colorScheme.secondary)
                LoadingIndicator(modifier = Modifier.padding(top = 32.dp))
            }
            Text(platform.version,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.BottomCenter))

        }
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    SahraPanelTheme {
        SplashScreen(
            platform = object : Platform {
                override val name = "Android"
                override val version = BuildKonfig.APP_VERSION
                override val type = PlatformType.ANDROID
            }
        )
    }
}

