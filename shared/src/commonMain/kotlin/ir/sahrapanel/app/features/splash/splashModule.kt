package ir.sahrapanel.app.features.splash

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val splashModule = module{
    viewModel<SplashViewModel>()
}