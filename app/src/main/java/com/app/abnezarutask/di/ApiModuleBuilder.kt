package com.app.abnezarutask.di

import com.app.abnezarutask.db.DatabaseAdapterProvider
import com.app.abnezarutask.network.ApiServiceProvider
import org.koin.dsl.module

val apiModule = module {
    single { ApiServiceProvider.provideApiServices(get()) }
    single { DatabaseAdapterProvider.provideDatabase(get()) }
}