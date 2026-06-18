package ir.sahrapanel.app.features.splash

import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val splashModule = module{
    viewModel<SplashViewModel>()
}