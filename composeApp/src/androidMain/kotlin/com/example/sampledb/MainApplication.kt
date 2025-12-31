package com.example.sampledb

import android.app.Application
import com.example.sampledb.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
            modules(module { single { DatabaseDriverFactory(get()) } })
        }
    }
}