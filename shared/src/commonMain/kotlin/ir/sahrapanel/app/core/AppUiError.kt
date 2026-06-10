package ir.sahrapanel.app.core

import androidx.compose.runtime.Composable
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.core.ui.components.asString


interface ErrorTarget
sealed interface AppUiError<out T : ErrorTarget> {
    val message: TranslatableText
    data class FieldError<T: ErrorTarget>(
        val target: T,
        override val message: TranslatableText
    ) : AppUiError<T>
}

fun AppUiError<*>.hasErrorFor(errorTarget: ErrorTarget): Boolean {
    return this is AppUiError.FieldError<*> && this.target == errorTarget
}

@Composable
fun AppUiError<*>.errorMessageFor(errorTarget: ErrorTarget): String? {
    if (this is AppUiError.FieldError<*> && this.target == errorTarget) {
        return this.message.asString() // Assuming this handles localized strings
    }
    return null
}