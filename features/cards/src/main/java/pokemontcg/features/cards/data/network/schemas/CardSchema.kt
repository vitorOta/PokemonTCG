package pokemontcg.features.cards.data.network.schemas

import com.google.gson.annotations.SerializedName
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.MapTo

internal class CardSchema(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val imageUrl: String
) : MapTo<Card> {
    override fun mapTo() = Card(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}