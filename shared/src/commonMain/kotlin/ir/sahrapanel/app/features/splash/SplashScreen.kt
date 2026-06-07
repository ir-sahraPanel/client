package ir.sahrapanel.app.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.Platform
import ir.sahrapanel.app.core.ui.theme.SahraPanelTheme
import ir.sahrapanel.app.getPlatform
import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute : NavKey

// ✅ receiver باید Any باشد نه SplashScreen
fun EntryProviderScope<NavKey>.splashEntry() {
    entry<SplashRoute> {
            SplashScreen(getPlatform())
    }
}

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
        SplashScreen(getPlatform())
    }
}

