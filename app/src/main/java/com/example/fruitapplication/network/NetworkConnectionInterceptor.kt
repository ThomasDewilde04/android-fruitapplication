package com.example.fruitapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

/**
 * Interceptor class responsible for checking network connectivity before making HTTP requests.
 *
 * @param context The Context object used to access system services.
 */
class NetworkConnectionInterceptor(val context: Context) : Interceptor {

    /**
     * Intercepts the HTTP request chain and checks for network connectivity.
     *
     * @param chain The intercepted chain of HTTP requests.
     * @return Response The response obtained after the request is executed.
     * @throws IOException Throws an IOException when there is no network connectivity.
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        if(!isConnected(context=context)){
            Log.i("retrofit", "there is no connection")
            throw IOException()

        }
        else {
            val builder = chain.request().newBuilder()
            return@run chain.proceed(builder.build())
        }

    }

    /**
     * Checks the current network connectivity.
     *
     * @param context The Context object used to access system services.
     * @return Boolean Returns `true` if there is an active network connection, otherwise `false`.
     */
    fun isConnected(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}