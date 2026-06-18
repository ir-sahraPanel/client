package ir.sahrapanel.app.core.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.plugin.module.dsl.startKoin

object AppKoin
fun initKoin(config : KoinAppDeclaration? = null){
    startKoin<AppKoin>() {
          config?.invoke(this)
        modules(
            databaseModule,
              appModule,
              platformModule,
              networkModule,
            featuresModule
        )
      }
}
