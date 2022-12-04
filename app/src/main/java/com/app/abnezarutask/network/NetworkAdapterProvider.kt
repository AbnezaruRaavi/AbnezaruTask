package com.app.abnezarutask.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkAdapterProvider
{

    const val connectTimeout: Long = 20 // 20s
    const val readTimeout: Long = 20 // 20s

    fun provideHttpClient(): OkHttpClient
    {
        val okHttpClientBuilder = OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit
    {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }
}

