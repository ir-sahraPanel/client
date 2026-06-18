package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration.DESKTOP
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration.MOBILE_LANDSCAPE
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration.MOBILE_PORTRAIT
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration.TABLET_LANDSCAPE
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration.TABLET_PORTRAIT


val WindowSizeClass.isDesktopOrTablet: Boolean
    get() = this.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

val WindowSizeClass.isMobile: Boolean
    get() = !this.isDesktopOrTablet


@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    return currentWindowAdaptiveInfoV2().windowSizeClass
}

val WindowSizeClass.adaptiveGridColumnCount: Int
    get() = when {
        this.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 2
        else -> 1
    }

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    val isMobile: Boolean get() = this == MOBILE_PORTRAIT || this == MOBILE_LANDSCAPE
    val isTablet: Boolean get() = this == TABLET_PORTRAIT || this == TABLET_LANDSCAPE
    val isDesktop: Boolean get() = this == DESKTOP
    val isPortrait: Boolean get() = this == MOBILE_PORTRAIT || this == TABLET_PORTRAIT
    val isLandscape: Boolean get() = this == MOBILE_LANDSCAPE || this == TABLET_LANDSCAPE
    val isCompact: Boolean get() = isMobile
    val isLarge: Boolean get() = isTablet || isDesktop
}

fun WindowSizeClass.toDeviceConfiguration(): DeviceConfiguration {
    val isWideEnoughForTablet =
        isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)   // ≥ 840
    val isWideEnoughForDesktop =
        isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND)     // ≥ 1200
    val isTallEnoughForPortrait =
        isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) // ≥ 900

    return when {
        isWideEnoughForDesktop -> DESKTOP
        isWideEnoughForTablet && isTallEnoughForPortrait -> TABLET_PORTRAIT
        isWideEnoughForTablet -> TABLET_LANDSCAPE
        isTallEnoughForPortrait -> MOBILE_PORTRAIT
        else -> MOBILE_LANDSCAPE
    }
}

fun DeviceConfiguration.contentMaxWidth(): Dp = when (this) {
    MOBILE_PORTRAIT -> WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND.dp    // 600
    MOBILE_LANDSCAPE -> WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND.dp  // 840
    TABLET_PORTRAIT -> WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND.dp  // 840
    TABLET_LANDSCAPE -> WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND.dp     // 1200
    DESKTOP -> WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND.dp     // 1200
}

fun Modifier.adaptiveWidth(device: DeviceConfiguration): Modifier =
    this
        .widthIn(max = device.contentMaxWidth())
        .fillMaxWidth()

fun Modifier.adaptiveCentered(device: DeviceConfiguration): Modifier =
    this
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .widthIn(max = device.contentMaxWidth())


