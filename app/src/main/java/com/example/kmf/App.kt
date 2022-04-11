package com.example.kmf

import android.app.Application
import com.example.kmf.di.databaseModule
import com.example.kmf.di.providerModule
import com.example.kmf.di.viewModelModule
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@DelicateCoroutinesApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(providerModule, viewModelModule, databaseModule))
        }
    }

}