package ir.sahrapanel.app.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.errorMessageFor
import ir.sahrapanel.app.core.hasErrorFor
import ir.sahrapanel.app.core.ui.components.HFillSpacer
import ir.sahrapanel.app.core.ui.components.VSpacer
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.change_phone_number
import ir.sahrapanel.app.shared.did_not_receive_code
import ir.sahrapanel.app.shared.login
import ir.sahrapanel.app.shared.resend_code
import ir.sahrapanel.app.shared.timer_text
import ir.sahrapanel.app.shared.verification_code
import ir.sahrapanel.app.shared.verification_code_send
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object AuthOtpConfirmRoute : NavKey {
}

fun EntryProviderScope<NavKey>.authOtpConfirmEntry(
    onNavigateToHome: () -> Unit,
    onNavigateToPhoneEntry: () -> Unit
) {
    entry<AuthOtpConfirmRoute> {
        val viewModel = koinViewModel<AuthViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        AuthOtpConfirmScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
        LaunchedEffect(Unit) {
            viewModel.onEvent(AuthEvent.StartResendTimer)
            viewModel.effect.collect {
                when (it) {
                    AuthEffect.NavigateToHome -> onNavigateToHome()
                    AuthEffect.NavigateToPhoneEntry -> onNavigateToPhoneEntry()
                    else -> return@collect
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AuthOtpConfirmScreen(
    state: AuthUiState,
    onEvent: (AuthEvent) -> Unit
) {
    val navigator = rememberSupportingPaneScaffoldNavigator(
        scaffoldDirective = calculatePaneScaffoldDirective(currentWindowAdaptiveInfoV2()).copy(
            maxVerticalPartitions = 1,
            horizontalPartitionSpacerSize = 0.dp
        )
    )

    BoxWithConstraints {
        val mainPaneWidth = maxWidth / 3
        val supportingPaneWidth = maxWidth * 2 / 3

        SupportingPaneScaffold(
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            mainPane = {
                AnimatedPane(modifier = Modifier.preferredWidth(mainPaneWidth)) {
                    OtpMainContent(state, onEvent)
                }
            },
            supportingPane = {
                AnimatedPane(modifier = Modifier.preferredWidth(supportingPaneWidth)) {
                    OtpSupportingContent()
                }
            }
        )
    }
}

@Composable
private fun OtpMainContent(state: AuthUiState, onEvent: (AuthEvent) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(Res.string.login)) }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(Res.string.verification_code),
                    style = MaterialTheme.typography.headlineMedium
                )
                VSpacer(16.dp)

                Text(
                    stringResource(Res.string.verification_code_send, state.phoneNumber),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                VSpacer(16.dp)


                // Input Field
                OutlinedTextField(
                    value = state.enteredOtpCode,
                    onValueChange = {
                        onEvent(AuthEvent.OtpChanged(it))
                        onEvent(AuthEvent.ClearError)
                    },
                    label = { Text(stringResource(Res.string.verification_code)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading,
                    isError = state.error.hasErrorFor(AuthErrorTarget.OtpCode),
                    supportingText = {
                        Text(state.error.errorMessageFor(AuthErrorTarget.OtpCode))
                    }
                )
                VSpacer(16.dp)
                ResendCodeSection(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    state,
                    onEvent
                )

                VSpacer(32.dp)
                Button(
                    onClick = { onEvent(AuthEvent.ConfirmOtp) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(Res.string.login))
                }
                TextButton(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(Res.string.change_phone_number))
                }
            }
        }
    }
}


fun Int.toTwoDigits(): String = if (this < 10) "0$this" else this.toString()

@Composable
fun ResendCodeSection(
    modifier: Modifier,
    state: AuthUiState,
    onEvent: (AuthEvent) -> Unit
) {
    val isTimerFinished = state.timerValue == 0

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val minutes = (state.timerValue / 60).toTwoDigits()
        val seconds = (state.timerValue % 60).toTwoDigits()
        val formattedTime = "$minutes:$seconds"

        Text(
            text = if (isTimerFinished) {
                stringResource(Res.string.did_not_receive_code)
            } else {
                stringResource(Res.string.timer_text, formattedTime)
            },
            style = MaterialTheme.typography.bodyMedium
        )

        HFillSpacer()
        TextButton(
            onClick = {
                onEvent(AuthEvent.ResendOtp)
            },
            enabled = isTimerFinished
        ) {
            Text(
                text = stringResource(Res.string.resend_code),
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }
}