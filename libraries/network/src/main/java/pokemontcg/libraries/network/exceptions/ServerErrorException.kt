package pokemontcg.libraries.network.exceptions

class ServerErrorException(message: String? = null, cause: Throwable? = null) :
    MyNetworkException(message, cause)