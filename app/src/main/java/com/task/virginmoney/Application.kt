package com.task.virginmoney

import netModule
import android.app.Application
import com.task.virginmoney.data.module.domainModule
import com.task.virginmoney.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import repositoryModule

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        createModuleDependency()
    }

    private fun createModuleDependency() {
        startKoin {
            androidContext(this@Application)
            modules(
                repositoryModule,
                netModule,
                domainModule,
                viewModelModule
            )
        }
    }
}