package pokemontcg.features.cards.data.network.schemas

import com.google.gson.annotations.SerializedName

internal class ListCardResponse(
    @SerializedName("cards")
    val cards: List<CardSchema>
)