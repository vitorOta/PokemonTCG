package pokemontcg.features.cards.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.common.ViewState
import pokemontcg.libraries.ui_components.BaseViewModel
import pokemontcg.libraries.ui_components.SingleLiveEvent
import timber.log.Timber
import kotlin.random.Random

internal class MainViewModel(private val listCardsUseCase: ListCardsUseCase) : BaseViewModel() {

    var isInitialized = false
        private set

    val batch = HashMap<String, MutableLiveData<Pair<Card, ViewState<Boolean>>>>()

    val fillListCards = SingleLiveEvent<List<LiveData<Pair<Card, ViewState<Boolean>>>>>()

    suspend fun init() {
        if (!isInitialized) {
            isInitialized = doAsyncWorkWithReturn {
                val list = listCardsUseCase.execute(null).take(20)

                list.forEach {
                    val liveData = MutableLiveData<Pair<Card, ViewState<Boolean>>>().apply {
                        value = Pair(it, ViewState.NotInitialized<Boolean>())
                    }
                    batch[it.id] = liveData

                    Timber.d("AAAA_$it")
                }
                updateCards()

                true
            } ?: false

            startBatch()
        }
    }

    private fun startBatch() {
        batch.values.forEach() {
            viewModelScope.launch {
                searchCardStatus(it.value!!.first)
            }
        }
    }

    private fun updateCards() {
        fillListCards.call(batch.map {
            it.value
        }.toMutableList())
    }


    suspend fun searchCardStatus(card: Card) {
        withContext(Dispatchers.Default) {
            batch[card.id]?.postValue(Pair(card, ViewState.Loading<Boolean>()))
            Timber.d("Searching_card ${card.id} - ${card.name}")
            delay(1_000 * (Random.nextLong(3) + 2))
            batch[card.id]?.postValue(Pair(card, ViewState.Success(Random.nextInt(55) % 2 == 0)))
        }

    }
}