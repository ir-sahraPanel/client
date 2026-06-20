package ir.sahrapanel.app.core.di

import io.ktor.client.HttpClient
import ir.sahrapanel.app.core.data.data_source.remote.network.createKtorClient
import ir.sahrapanel.app.features.auth.data.AuthRemoteDataSource
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single


val networkModule = module {
    single<HttpClient>() {
        create(::createKtorClient)
    }
    single<AuthRemoteDataSource>()
}