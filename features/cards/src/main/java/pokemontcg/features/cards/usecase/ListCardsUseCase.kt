package pokemontcg.features.cards.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pokemontcg.features.cards.data.network.CardsApi
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.UseCase
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.RequestManager

internal class ListCardsUseCase :
    UseCase<Unit?, List<Card>> {

    override suspend fun execute(param: Unit?): List<Card> {
        return withContext(Dispatchers.IO) {
            val api = ApiClientBuilder.createServiceApi(CardsApi::class.java)
            val apiResponse = RequestManager.requestFromApi { api.listCards() }
            val cards = apiResponse?.cards?.map {
                Card(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl
                )
            }
            cards ?: emptyList()
        }
    }
}