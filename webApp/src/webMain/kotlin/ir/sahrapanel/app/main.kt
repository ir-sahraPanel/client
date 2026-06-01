package ir.sahrapanel.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ir.sahrapanel.app.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        initKoin()
        App()
    }
}