package ir.sahrapanel.app.core.location

import ir.sahrapanel.app.core.location.data.LocationDataSource
import ir.sahrapanel.app.core.location.data.LocationRepositoryImpl
import ir.sahrapanel.app.core.location.domain.LocationRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single

val locationModule  = module {
    singleOf(::LocationDataSource)
    singleOf(::LocationRepositoryImpl) bind LocationRepository::class
}