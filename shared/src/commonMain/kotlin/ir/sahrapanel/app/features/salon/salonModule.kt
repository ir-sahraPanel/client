package ir.sahrapanel.app.features.salon

import ir.sahrapanel.app.features.salon.data.SalonDataSource
import ir.sahrapanel.app.features.salon.data.SalonRepositoryImpl
import ir.sahrapanel.app.features.salon.domain.SalonRepository
import ir.sahrapanel.app.features.salon.presentation.create.CreateSalonViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel

val salonModule = module {
    singleOf(::SalonDataSource)
    singleOf(::SalonRepositoryImpl)  bind  SalonRepository::class
    viewModelOf(::CreateSalonViewModel)
}