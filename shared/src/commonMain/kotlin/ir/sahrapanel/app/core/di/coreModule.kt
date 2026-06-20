package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.data.data_source.remote.SalonDataSource
import ir.sahrapanel.app.core.data.repository.SalonRepositoryImpl
import ir.sahrapanel.app.core.domain.repository.SalonRepository
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single

val coreModule = module {
    single<SalonDataSource>()
    single<SalonRepository>() { create(::SalonRepositoryImpl) }

}