package pokemontcg.libraries.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

internal class BasicLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("url chamada: ${request.url()}")

        val response = chain.proceed(request)

        Timber.d("url retornada(${response.code()}): ${request.url()}")

        return response
    }
}