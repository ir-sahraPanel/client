package ir.sahrapanel.app.features.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.HingePolicy
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.errorMessageFor
import ir.sahrapanel.app.core.hasErrorFor
import ir.sahrapanel.app.core.ui.components.VSpacer
import ir.sahrapanel.app.core.ui.drawable.SahraPanelDrawable
import ir.sahrapanel.app.core.ui.drawable.logoPlaceHolder
import ir.sahrapanel.app.core.ui.theme.SahraPanelTheme
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.enter_phone_number
import ir.sahrapanel.app.shared.login
import ir.sahrapanel.app.shared.phone_number
import ir.sahrapanel.app.shared.request_otp
import ir.sahrapanel.app.shared.welcome
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object AuthEnterPhoneNumberRoute : NavKey

fun EntryProviderScope<NavKey>.authPhoneNumberEntry(navigateToOtpConfirm: () -> Unit) {
    entry<AuthEnterPhoneNumberRoute> {
        val viewModel = koinViewModel<AuthViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        AuthEnterPhoneNumberScreen(
            state = state, onEvent = viewModel::onEvent
        )
        LaunchedEffect(Unit) {
            viewModel.effect.collect() {
                when (it) {
                    AuthEffect.NavigateToConfirmOtp -> navigateToOtpConfirm()

                    else -> return@collect
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AuthEnterPhoneNumberScreen(
    state: AuthUiState, onEvent: (AuthEvent) -> Unit
) {
    // 1. Calculate the default directive based on current window size
    val defaultDirective = calculatePaneScaffoldDirective(
        currentWindowAdaptiveInfoV2(), HingePolicy.AvoidSeparating
    )

    // 2. Customize it so it ALWAYS splits horizontally (side-by-side)
    // and never stacks vertically (bottom)
    val customDirective = defaultDirective.copy(
        maxVerticalPartitions = 1,   // Prevent stacking panes on top of each other
        horizontalPartitionSpacerSize = 0.dp
    )

    // 3. Pass the custom directive to the navigator
    val navigator = rememberSupportingPaneScaffoldNavigator(
        scaffoldDirective = customDirective,

        )

    BoxWithConstraints {
        val totalWidth = maxWidth
        val mainPaneWidth = totalWidth / 2       // 1x
        val supportingPaneWidth = totalWidth * 2 / 3   // 2x

        SupportingPaneScaffold(
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,

            mainPane = {
                AnimatedPane(modifier = Modifier.preferredWidth(mainPaneWidth)) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(stringResource(Res.string.login)) })
                        }) { paddingValues ->
                        Column(
                            modifier = Modifier.padding(paddingValues).fillMaxSize()
                                .verticalScroll(rememberScrollState()).padding(16.dp).imePadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                SahraPanelDrawable.logoPlaceHolder,
                                contentDescription = ""
                            )
                            VSpacer(42.dp)
                            Text(
                                stringResource(Res.string.welcome),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            VSpacer(16.dp)
                            Text(
                                stringResource(Res.string.enter_phone_number),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                            VSpacer(16.dp)

                            OutlinedTextField(
                                value = state.phoneNumber,
                                onValueChange = {
                                    onEvent(AuthEvent.PhoneChanged(it))
                                },
                                maxLines = 1,

                                label = { Text(stringResource(Res.string.phone_number)) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth(0.7f),
                                isError = state.errors.hasErrorFor(AuthErrorTarget.PhoneNumber),
                                enabled = !state.isLoading,
                                supportingText = {
                                    state.errors.errorMessageFor(AuthErrorTarget.PhoneNumber)
                                        ?.let { Text(it) }
                                }
                            )
                            VSpacer(32.dp)
                            Button(
                                onClick = { onEvent(AuthEvent.RequestOtp) },
                                modifier = Modifier.fillMaxWidth(0.7f),
                                enabled = !state.isLoading
                            ) {
                                if (state.isLoading) LoadingIndicator()
                                else Text(stringResource(Res.string.request_otp))
                            }
                            VSpacer(8.dp)
                            Text(
                                "ورود شما به معنای پذیرش [قوانین] و [سیاست حریم خصوصی] است.",
                                modifier = Modifier.fillMaxWidth(0.7f),
                            )

                        }
                    }
                }
            },
            supportingPane = {
                AnimatedPane(modifier = Modifier.preferredWidth(supportingPaneWidth)) {
                    OtpSupportingContent()
                }
            })
    }
}

@Composable
@Preview
private fun AuthEnterPhoneNumberScreenPreview() {
    SahraPanelTheme {
        AuthEnterPhoneNumberScreen(AuthUiState(), {})
    }
}