package com.simplez.ctvbox

import android.app.Application
import com.simplez.ctvbox.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CTvBoxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CTvBoxApplication)
            modules(appModules)
        }
    }
}
