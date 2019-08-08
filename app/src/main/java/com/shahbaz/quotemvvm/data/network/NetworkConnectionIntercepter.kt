package com.shahbaz.quotemvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.shahbaz.quotemvvm.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionIntercepter(val context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable())
            throw  NoInternetException("make sure you have an active data connection?")
        return chain.proceed(chain.request())

    }

    fun isInternetAvailable(): Boolean {

        var connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}