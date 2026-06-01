package ir.sahrapanel.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.plugin.module.dsl.startKoin

class KoinApp

fun initKoin(config : KoinAppDeclaration? = null){
    startKoin<KoinApp> {
        config?.invoke(this)
        modules(databaseModule)
    }
}