package com.app.abnezarutask

import android.app.Application
import com.app.abnezarutask.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }
}