package pokemontcg.libraries.network

import pokemontcg.libraries.network.exceptions.MyNetworkException
import pokemontcg.libraries.network.exceptions.ServerErrorException
import retrofit2.Response
import timber.log.Timber

object RequestManager {
    suspend fun <T> requestFromApi(
        request: (suspend () -> Response<T>)
    ): T? {
        try {
            val response = request()
            if (response.isSuccessful) {
                //aqui pode acontecer de ter algum crash relacionado à conversão de json
                return response.body()
            } else {
                val message = response.message()

                throw when (response.code()) {
                    500 -> ServerErrorException(message)
                    else -> MyNetworkException(message)
                }
            }

        } catch (e: Exception) {
            Timber.e(e, "Request error: ${e.message}")
            throw e
        }
    }
}