package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import ir.sahrapanel.app.core.ui.drawable.CircleXmark
import ir.sahrapanel.app.core.ui.drawable.SahraPanelDrawable
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.something_went_wrong
import ir.sahrapanel.app.shared.try_again
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorContainer(
    message: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)? = null,
    windowSizeClass: WindowSizeClass = rememberWindowSizeClass()
) {
    if (windowSizeClass.isMobile) {
        ErrorModalBottomSheet(
            message = message,
            onDismiss = onDismiss,
            onRetry = onRetry
        )
    } else {
        ErrorAlertDialog(
            message = message,
            onDismiss = onDismiss,
            onRetry = onRetry
        )
    }
}

// ── Mobile: ModalBottomSheet ──────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ErrorModalBottomSheet(
    message: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)?
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.errorContainer,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        BottomSheetErrorContent(
            message = message,
            onDismiss = onDismiss,
            onRetry = onRetry,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
        )
    }
}

// ── Tablet / Desktop: AlertDialog ─────────────────────────────────────────────

@Composable
private fun ErrorAlertDialog(
    message: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)?
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.errorContainer,
        icon = {
            Icon(
                SahraPanelDrawable.CircleXmark,
                contentDescription = stringResource(Res.string.something_went_wrong)
            )
        },
        title = {
            Text(
                text = stringResource(Res.string.something_went_wrong),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = LocalTextStyle.current.color.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            if (onRetry != null) {
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Try again")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text("Dismiss")
            }
        }
    )
}

// ── Shared content (used only by the bottom sheet) ───────────────────────────

@Composable
private fun BottomSheetErrorContent(
    message: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            SahraPanelDrawable.CircleXmark,
            contentDescription = stringResource(Res.string.something_went_wrong)
        )
        VSpacer(8.dp)
        Text(
            text = stringResource(Res.string.something_went_wrong),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = LocalTextStyle.current.color.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )

        if (onRetry != null) {
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.try_again))
            }
        }

        TextButton(
            onClick = onDismiss,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dismiss")
        }
    }
}