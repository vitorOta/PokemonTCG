package pokemontcg.libraries.ui_components

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = SingleLiveEvent<String>()
    val showError: LiveData<String> = _showError

    protected fun doAsyncWork(
        work: suspend () -> Unit
    ) {
        viewModelScope.launch {
            doWork(work)
        }
    }

    protected suspend fun <T> doAsyncWorkWithReturn(
        work: suspend () -> T
    ): T? {
        return withContext(viewModelScope.coroutineContext) {
            doWork(work)
        }
    }

    private suspend fun <T> doWork(
        work: suspend () -> T
    ): T? {
        return try {
            setLoading(true)
            work()
        } catch (e: Exception) {
            Timber.e(e)
            setError(e.message)
            null
        } finally {
            setLoading(false)
        }
    }


    @VisibleForTesting
    fun setError(message: String?) {
        _showError.call(message)
    }

    @VisibleForTesting
    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

}