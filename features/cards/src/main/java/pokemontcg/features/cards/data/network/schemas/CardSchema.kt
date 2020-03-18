package pokemontcg.features.cards.data.network.schemas

import com.google.gson.annotations.SerializedName

internal class CardSchema(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val imageUrl: String
)