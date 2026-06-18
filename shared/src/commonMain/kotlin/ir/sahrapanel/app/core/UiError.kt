package ir.sahrapanel.app.core

import androidx.compose.runtime.Composable
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.core.ui.components.asString


interface ErrorTarget
typealias UiErrors<T> = Map<T, TranslatableText>

fun <T : ErrorTarget> UiErrors<T>.hasErrorFor(target: T): Boolean =
    containsKey(target)

@Composable
fun <T : ErrorTarget> UiErrors<T>.errorMessageFor(target: T): String? =
    get(target)?.asString()