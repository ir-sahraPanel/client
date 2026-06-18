package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OutlinedTextFieldLabel(
    text: String,
    modifier: Modifier = Modifier,
    isRequired: Boolean
) {
    Row(modifier = modifier) {
        if (isRequired) {
            Text(text = text)
            Text(
                text = " *",
                color = MaterialTheme.colorScheme.error
            )
        } else {
            // Appends the (اختیاری) suffix if the field is optional
            Text(text = "$text (اختیاری)")
        }
    }
}