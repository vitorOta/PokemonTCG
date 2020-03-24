package pokemontcg.features.cards.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.ui_components.BaseViewModel

internal class MainViewModel : BaseViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private var isInitialized = false

    fun init() {
        if (!isInitialized) {
            isInitialized = true
            doAsyncWork {
                _cards.value = ListCardsUseCase().execute(null)
                Log.d("VIEWMODEL_AAA", "iniciou o viewmodel")
            }
        }
    }
}