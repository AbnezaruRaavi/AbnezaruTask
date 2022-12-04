package com.app.abnezarutask.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.app.abnezarutask.R
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException(applicationContext.getString(R.string.no_network_message))
        val req: Request?
        try {
            req = chain.request()
        } catch (e: Exception) {
            throw ApiException(applicationContext.getString(R.string.unknown_exception))
        }
        return chain.proceed(req)
    }

    private fun isInternetAvailable(): Boolean {
        var result = false

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
        }
        return result
    }
}