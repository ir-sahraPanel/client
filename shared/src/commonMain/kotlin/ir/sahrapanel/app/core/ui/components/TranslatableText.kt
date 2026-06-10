package ir.sahrapanel.app.core.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface TranslatableText {
    // For local localized string resources (strings.xml)
    data class Resource(val res: StringResource) : TranslatableText
    
    // For raw strings coming directly from the backend server
    data class DynamicString(val value: String) : TranslatableText
}
@Composable
fun TranslatableText.asString(): String {
    return when (this) {
        is TranslatableText.DynamicString -> this.value
        is TranslatableText.Resource -> stringResource(this.res)
    }
}
