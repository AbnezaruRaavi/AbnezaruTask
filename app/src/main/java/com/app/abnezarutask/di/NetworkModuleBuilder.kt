package com.app.abnezarutask.di

import com.app.abnezarutask.R
import com.app.abnezarutask.network.NetworkAdapterProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single { NetworkAdapterProvider.provideHttpClient() }
    single {
        val baseUrl = androidContext().getString(R.string.BASE_URL)
        NetworkAdapterProvider.provideRetrofit(get(), baseUrl)
    }
}