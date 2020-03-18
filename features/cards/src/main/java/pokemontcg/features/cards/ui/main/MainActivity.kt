package pokemontcg.features.cards.ui.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.cards_activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pokemontcg.features.cards.R
import pokemontcg.features.cards.data.network.CardsApi
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.RequestManager

class MainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_activity_main)

        setupView()
        listCards()
    }

    private fun setupView() {
        rvCards.adapter = cardsAdapter
    }

    private fun listCards() {
        val api = ApiClientBuilder.createServiceApi(CardsApi::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.listCards() }

            val cards = apiResponse?.cards?.map {
                Card(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl
                )
            }

            launch(Dispatchers.Main) {
                cardsAdapter.submitList(cards)
            }
        }
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .show()
    }
}