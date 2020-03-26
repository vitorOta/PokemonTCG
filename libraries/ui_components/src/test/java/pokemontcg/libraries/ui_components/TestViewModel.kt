package pokemontcg.libraries.ui_components

class ViewModelTest : BaseViewModel() {
    fun callSuccess(work: () -> Unit) {
        doAsyncWork { work() }
    }

    fun callError(errorMessage: String) {
        doAsyncWork {
            throw Exception(errorMessage)
        }
    }
}