package com.app.abnezarutask.network

import retrofit2.Retrofit

object ApiServiceProvider {

    fun provideApiServices(retrofit: Retrofit) : ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}