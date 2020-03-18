package pokemontcg.libraries.network.exceptions

class ServerErrorException(message: String?, cause: Throwable? = null) :
    MyNetworkException(message, cause)