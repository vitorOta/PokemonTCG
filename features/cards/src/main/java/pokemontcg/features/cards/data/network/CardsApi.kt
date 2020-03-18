package pokemontcg.features.cards.data.network


import pokemontcg.features.cards.data.network.schemas.ListCardResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CardsApi {
    @GET("cards")
    suspend fun listCards(): Response<ListCardResponse>
}