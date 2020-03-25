package pokemontcg.libraries.ui_components

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = SingleLiveEvent<String>()
    val showError: LiveData<String> = _showError

    fun doAsyncWork(work: suspend () -> Unit) {
        viewModelScope.launch {
            setLoading(true)
            try {
                work()
            } catch (e: Exception) {
                setError(e.message)
                Timber.e(e.message)
            }
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