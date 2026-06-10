package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ColumnScope.VSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun ColumnScope.VFillSpacer(minHeight: Dp = 16.dp) {
    Spacer(
        modifier = Modifier
            .heightIn(min = minHeight)
            .weight(1f)
    )
}

@Composable
fun RowScope.HSpacer(width: Dp = 16.dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun RowScope.HFillSpacer(minWidth: Dp = 16.dp) {
    Spacer(
        modifier = Modifier
            .widthIn(min = minWidth)
            .weight(1f)
    )
}