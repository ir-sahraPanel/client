package ir.sahrapanel.app.features.auth

import ir.sahrapanel.app.features.auth.data.repository.AuthRepositoryImpl
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository
import ir.sahrapanel.app.features.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
val authModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    viewModelOf(::AuthViewModel)
}