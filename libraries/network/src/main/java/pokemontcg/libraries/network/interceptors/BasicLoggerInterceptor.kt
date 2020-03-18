package pokemontcg.libraries.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

internal class BasicLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("BasicLoggerInterceptor", "url chamada: ${request.url()}")

        val response = chain.proceed(request)

        Log.d("BasicLoggerInterceptor", "url retornada(${response.code()}): ${request.url()}")

        return response
    }
}