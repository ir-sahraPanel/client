package ir.sahrapanel.app.features.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ir.sahrapanel.app.core.ui.components.VSpacer
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.app_desc
import ir.sahrapanel.app.shared.app_name
import ir.sahrapanel.app.shared.img
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtpSupportingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painterResource(Res.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(
            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f))
        )))
        Column(modifier = Modifier.padding(32.dp)) {
            Text(stringResource(Res.string.app_name), style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.primary)
            VSpacer(16.dp)
            Text(stringResource(Res.string.app_desc), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
        }
    }
}