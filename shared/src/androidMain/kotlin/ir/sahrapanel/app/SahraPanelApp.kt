package ir.sahrapanel.app

import android.app.Application
import ir.sahrapanel.app.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class SahraPanelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SahraPanelApp)

        }
    }
}