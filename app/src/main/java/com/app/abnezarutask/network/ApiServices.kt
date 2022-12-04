package com.app.abnezarutask.network

import com.app.abnezarutask.models.MatchesApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("api/")
    suspend fun getResults(@Query("results") result: Int) : Response<MatchesApiResponseModel>
}