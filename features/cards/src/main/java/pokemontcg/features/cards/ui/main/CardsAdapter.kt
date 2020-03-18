package pokemontcg.features.cards.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cards_item_card.view.*
import pokemontcg.features.cards.R
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.ui.extensions.loadImage

internal class CardsAdapter : ListAdapter<Card, CardsAdapter.ViewHolder>(DIFF_UTIL) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cards_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        with(holder.itemView) {
            cards_tvName.text = card.name
            cards_imageview.loadImage(card.imageUrl)
        }
    }
}

private object DIFF_UTIL : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}