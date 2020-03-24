package pokemontcg.libraries.ui_components

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = SingleLiveEvent<String>()
    val showError: LiveData<String> = _showError

    fun doAsyncWork(work: suspend () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                work()
            } catch (e: Exception) {
                _showError.call(e.message)
                Log.e("BaseViewModel", e.message)
            }
            _isLoading.value = false
        }
    }

}