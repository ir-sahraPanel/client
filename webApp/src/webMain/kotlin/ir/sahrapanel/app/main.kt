package ir.sahrapanel.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ir.sahrapanel.app.core.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        initKoin()
        SahraPanel()
    }
}