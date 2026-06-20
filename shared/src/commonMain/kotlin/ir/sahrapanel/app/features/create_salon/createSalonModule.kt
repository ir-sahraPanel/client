package ir.sahrapanel.app.features.create_salon

import ir.sahrapanel.app.features.create_salon.presentation.CreateSalonViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val createSalonModule = module {
    viewModel<CreateSalonViewModel>()
}