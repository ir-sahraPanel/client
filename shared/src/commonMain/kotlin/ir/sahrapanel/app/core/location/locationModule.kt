package ir.sahrapanel.app.core.location

import ir.sahrapanel.app.core.location.data.LocationDataSource
import ir.sahrapanel.app.core.location.data.LocationRepositoryImpl
import ir.sahrapanel.app.core.location.domain.LocationRepository
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single

val locationModule  = module {
    single<LocationDataSource>()
    single<LocationRepository> { create(::LocationRepositoryImpl) }
}