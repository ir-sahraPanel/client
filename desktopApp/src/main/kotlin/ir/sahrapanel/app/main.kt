package ir.sahrapanel.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ir.sahrapanel.app.core.di.initKoin


fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "sahraPanel",
    ) {
        SahraPanel()
    }
}