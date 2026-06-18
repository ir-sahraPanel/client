package ir.sahrapanel.app.features.auth

import ir.sahrapanel.app.features.auth.data.repository.AuthRepositoryImpl
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository
import ir.sahrapanel.app.features.auth.presentation.AuthViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.viewModel

val authModule = module {
    single<AuthRepository>{
        create(::AuthRepositoryImpl)
    }
    viewModel<AuthViewModel>()
}