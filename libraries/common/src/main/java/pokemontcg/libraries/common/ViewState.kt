package pokemontcg.libraries.common

sealed class ViewState<T> {
    class NotInitialized<T> : ViewState<T>()
    class Loading<T> : ViewState<T>()
    data class Error<T>(val errorMessage: String? = null, val exception: Exception? = null) :
        ViewState<T>()

    data class Success<T>(val data: T) : ViewState<T>()

    override fun equals(other: Any?): Boolean {
        return this.javaClass == other?.javaClass
    }
}