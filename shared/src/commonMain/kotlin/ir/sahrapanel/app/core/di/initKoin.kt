package ir.sahrapanel.app.core.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.plugin.module.dsl.startKoin


object SahraPanelApp

fun initKoin(config : KoinAppDeclaration? = null){
  startKoin<SahraPanelApp> {
        config?.invoke(this)
        modules(databaseModule,
            appModule,
            secureStorageModule,
            platformModule,
            networkModule,
            featuresModule
        )
    }
}
