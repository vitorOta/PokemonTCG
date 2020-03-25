package pokemontcg.libraries.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pokemontcg.libraries.network.interceptors.BasicLoggerInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientBuilder {
    private val baseClient = OkHttpClient.Builder().build()

    private val defaultUrl = "https://api.pokemontcg.io/v1/"

    fun <T> createServiceApi(
        serviceClass: Class<T>,
        baseUrl: String = defaultUrl,
        gson: Gson = gsonDefault,
        vararg interceptors: Interceptor
    ): T {
        val clientBuilder = baseClient.newBuilder()

        clientBuilder.addInterceptor(BasicLoggerInterceptor())

        interceptors.forEach { clientBuilder.addInterceptor(it) }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(serviceClass)
    }
}