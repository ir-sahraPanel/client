package ir.sahrapanel.app.core.di

import io.ktor.client.HttpClient
import ir.sahrapanel.app.core.data.remote.createKtorClient
import ir.sahrapanel.app.features.auth.data.AuthRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val networkModule = module {
    single<HttpClient> {
        createKtorClient(
            get(),
            get(),
            get()
        )
    }
    singleOf(::AuthRemoteDataSource)
}