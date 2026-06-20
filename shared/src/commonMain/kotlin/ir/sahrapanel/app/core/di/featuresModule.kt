package ir.sahrapanel.app.core.di

import ir.sahrapanel.app.core.location.locationModule
import ir.sahrapanel.app.features.auth.authModule
import ir.sahrapanel.app.features.create_salon.createSalonModule
import ir.sahrapanel.app.features.splash.splashModule
import org.koin.dsl.module

val featuresModule = module{
    includes(
        authModule,
        splashModule,
        locationModule,
        createSalonModule

    )
}