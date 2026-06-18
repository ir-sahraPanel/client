package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun AdaptiveRowColumn(
    modifier : Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    val windowSize =rememberWindowSizeClass()
    val isDesktopOrTablet = windowSize.isDesktopOrTablet

    if (!isDesktopOrTablet) {
        Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment
        ) {
            content()
        }
    }
}


