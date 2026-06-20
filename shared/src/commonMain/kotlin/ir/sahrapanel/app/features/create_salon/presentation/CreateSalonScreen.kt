package ir.sahrapanel.app.features.create_salon.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.HingePolicy
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.errorMessageFor
import ir.sahrapanel.app.core.hasErrorFor
import ir.sahrapanel.app.core.domain.model.City
import ir.sahrapanel.app.core.domain.model.Province
import ir.sahrapanel.app.core.ui.components.DeviceConfiguration
import ir.sahrapanel.app.core.ui.components.ErrorContainer
import ir.sahrapanel.app.core.ui.components.FlowRowCard
import ir.sahrapanel.app.core.ui.components.InfoCard
import ir.sahrapanel.app.core.ui.components.MassageBottomSheet
import ir.sahrapanel.app.core.ui.components.OutlinedTextFieldLabel
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.core.ui.components.VSpacer
import ir.sahrapanel.app.core.ui.components.adaptiveCentered
import ir.sahrapanel.app.core.ui.components.adaptiveGridColumnCount
import ir.sahrapanel.app.core.ui.components.asString
import ir.sahrapanel.app.core.ui.components.rememberWindowSizeClass
import ir.sahrapanel.app.core.ui.theme.SahraPanelTheme
import ir.sahrapanel.app.features.auth.presentation.OtpSupportingContent
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.address
import ir.sahrapanel.app.shared.create_new_salon
import ir.sahrapanel.app.shared.first_name
import ir.sahrapanel.app.shared.last_name
import ir.sahrapanel.app.shared.new_salon_desc
import ir.sahrapanel.app.shared.salon_info
import ir.sahrapanel.app.shared.salon_name
import ir.sahrapanel.app.shared.salon_owner_info
import ir.sahrapanel.app.shared.submit
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object CreateSalonRoute : NavKey


fun EntryProviderScope<NavKey>.createSalonEntry(onNavToDashboardScreen: () -> Unit) {
    entry<CreateSalonRoute> {
        val viewModel = koinViewModel<CreateSalonViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        var showSuccessDialog by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<TranslatableText?>(null) }
        CreateSalonScreen(
            uiState, viewModel::onEvent
        )

        LaunchedEffect(Unit) {
            viewModel.effect.collect {
                when (it) {
                    is CreateSalonEffect.ShowErrorScreen -> errorMessage = it.message
                    CreateSalonEffect.ShowSuccessDialog -> showSuccessDialog = true
                    CreateSalonEffect.NavToDashboardScreen -> onNavToDashboardScreen()
                }
            }
        }


        if (errorMessage != null) {
            ErrorContainer(
                (errorMessage as TranslatableText).asString(), onDismiss = {
                 errorMessage = null
                })
        }

        if (showSuccessDialog) {
            MassageBottomSheet(onDismiss = {
                showSuccessDialog =false
            })
        }


    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CreateSalonScreen(
    uiState: CreateSalonUiState, onEvent: (CreateSalonEvent) -> Unit
) {
    val defaultDirective = calculatePaneScaffoldDirective(
        currentWindowAdaptiveInfoV2(), HingePolicy.AvoidSeparating
    )

    val customDirective = defaultDirective.copy(
        maxVerticalPartitions = 1,   // Prevent stacking panes on top of each other
        horizontalPartitionSpacerSize = 0.dp
    )

    // 3. Pass the custom directive to the navigator
    val navigator = rememberSupportingPaneScaffoldNavigator(
        scaffoldDirective = customDirective,

        )

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            CreateSalonMainPain(uiState, onEvent)
        },
        supportingPane = {
            OtpSupportingContent()
        })

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun CreateSalonMainPain(
    uiState: CreateSalonUiState, onEvent: (CreateSalonEvent) -> Unit
) {


    val windowSize = rememberWindowSizeClass()
    Scaffold(modifier = Modifier.imePadding().systemBarsPadding(), topBar = {
        TopAppBar(title = {
            Text(stringResource(Res.string.create_new_salon))
        })
    }, bottomBar = {
        Surface {

            Button(
                onClick = {
                    onEvent(CreateSalonEvent.Submit)
                },
                modifier = Modifier.adaptiveCentered(DeviceConfiguration.MOBILE_PORTRAIT)
                    .padding(16.dp).fillMaxWidth(),
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    LoadingIndicator()
                } else {
                    Text(stringResource(Res.string.submit))
                }
            }

        }
    }) { paddingValues ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(paddingValues)
                .fillMaxSize().padding(16.dp).adaptiveCentered(DeviceConfiguration.MOBILE_PORTRAIT)


        ) {
            InfoCard(message = stringResource(Res.string.new_salon_desc))
            VSpacer(16.dp)
            FlowRowCard(
                maxItemsInEachRow = windowSize.adaptiveGridColumnCount,
                title = stringResource(Res.string.salon_info)
            ) {

                OutlinedTextField(
                    value = uiState.salonName,
                    onValueChange = {
                        onEvent(CreateSalonEvent.SalonNameChanged(it))
                    },
                    isError = uiState.errors.hasErrorFor(CreateSalonErrorTarget.SalonName),
                    supportingText = {
                        uiState.errors.errorMessageFor(CreateSalonErrorTarget.SalonName)?.let {
                            Text(it)
                        }
                    },
                    label = {
                        OutlinedTextFieldLabel(
                            stringResource(Res.string.salon_name), isRequired = true
                        )
                    })

                ProvinceCityChooser(
                    provinces = uiState.provinces,
                    cities = uiState.cities,
                    selectedProvince = uiState.selectedProvince,
                    selectedCity = uiState.selectedCity,
                    provinceExpanded = uiState.isProvinceExpended,
                    cityExpanded = uiState.isCityExpended,
                    onProvinceExpandedChange = {
                        onEvent(CreateSalonEvent.ProvinceExpandedChange(it))
                    },
                    onCityExpandedChange = {
                        onEvent(CreateSalonEvent.CityExpandedChange(it))
                    },
                    onProvinceSelected = {
                        onEvent(CreateSalonEvent.ProvinceChanged(it))
                    },
                    onCitySelected = {
                        onEvent(CreateSalonEvent.CityChanged(it))
                    },
                    modifier = Modifier
                )
                OutlinedTextField(value = uiState.address, onValueChange = {
                    onEvent(CreateSalonEvent.SalonNameChanged(it))
                }, label = {
                    OutlinedTextFieldLabel(
                        stringResource(Res.string.address), isRequired = false
                    )
                })

            }


            VSpacer(16.dp)
            FlowRowCard(
                maxItemsInEachRow = windowSize.adaptiveGridColumnCount,
                title = stringResource(Res.string.salon_owner_info),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = uiState.adminFName,
                    onValueChange = {
                        onEvent(CreateSalonEvent.AdminFNameChange(it))
                    },
                    isError = uiState.errors.hasErrorFor(CreateSalonErrorTarget.AdminFName),
                    supportingText = {
                        uiState.errors.errorMessageFor(CreateSalonErrorTarget.AdminFName)?.let {
                            Text(it)
                        }
                    },
                    label = {
                        OutlinedTextFieldLabel(
                            stringResource(Res.string.first_name), isRequired = true
                        )
                    })

                OutlinedTextField(
                    value = uiState.adminLName,
                    onValueChange = {
                        onEvent(CreateSalonEvent.AdminLNameChange(it))
                    },
                    isError = uiState.errors.hasErrorFor(CreateSalonErrorTarget.AdminLName),
                    supportingText = {
                        uiState.errors.errorMessageFor(CreateSalonErrorTarget.AdminLName)?.let {
                            Text(it)
                        }
                    },
                    label = {
                        OutlinedTextFieldLabel(
                            stringResource(Res.string.last_name), isRequired = true
                        )
                    })

            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvinceCityChooser(
    provinces: List<Province>,
    cities: List<City>,
    selectedProvince: Province?,
    selectedCity: City?,
    provinceExpanded: Boolean,
    cityExpanded: Boolean,
    onProvinceExpandedChange: (Boolean) -> Unit,
    onCityExpandedChange: (Boolean) -> Unit,
    onProvinceSelected: (Province) -> Unit,
    onCitySelected: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = provinceExpanded, onExpandedChange = onProvinceExpandedChange
    ) {
        OutlinedTextField(
            value = selectedProvince?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Province") },
            placeholder = { Text("Select province") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(provinceExpanded) },
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true)
        )
        ExposedDropdownMenu(
            expanded = provinceExpanded, onDismissRequest = { onProvinceExpandedChange(false) }) {
            provinces.forEach { province ->
                DropdownMenuItem(
                    text = { Text(province.name) },
                    onClick = { onProvinceSelected(province) },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }

    ExposedDropdownMenuBox(
        expanded = cityExpanded && cities.isNotEmpty(),
        onExpandedChange = { if (cities.isNotEmpty()) onCityExpandedChange(it) }) {
        OutlinedTextField(
            value = selectedCity?.name ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = cities.isNotEmpty(),
            label = { Text("City") },
            placeholder = { Text("Select city") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(cityExpanded) },
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true)
        )
        ExposedDropdownMenu(
            expanded = cityExpanded && cities.isNotEmpty(),
            onDismissRequest = { onCityExpandedChange(false) }) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = { Text(city.name) },
                    onClick = { onCitySelected(city) },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
@PreviewScreenSizes
private fun CreateSalonScreenPreview() {
    SahraPanelTheme {
        CreateSalonScreen(CreateSalonUiState(), {})
    }
}