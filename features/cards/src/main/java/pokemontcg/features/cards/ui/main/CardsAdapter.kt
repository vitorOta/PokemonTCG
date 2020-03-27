package pokemontcg.features.cards.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cards_item_card.view.*
import pokemontcg.features.cards.R
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.ui.extensions.loadImage
import pokemontcg.libraries.common.ViewState

internal class CardsAdapter(private val onItemClick: (Card) -> Unit) :
    ListAdapter<LiveData<Pair<Card, ViewState<Boolean>>>, CardsAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cards_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var currentLiveData: LiveData<Pair<Card, ViewState<Boolean>>>? = null

        fun bind(item: LiveData<Pair<Card, ViewState<Boolean>>>, position: Int) {
            val lifecycleOwner = itemView.context as AppCompatActivity
            currentLiveData?.removeObservers(lifecycleOwner)
            currentLiveData = item
            item.observe(lifecycleOwner, Observer {
                val card = it.first
                val state = it.second

                with(itemView) {
                    cards_tvName.text = card.name
                    cards_imageview.loadImage(card.imageUrl)

                    val color = when (state) {
                        is ViewState.Error -> Color.DKGRAY
                        is ViewState.Success -> if (state.data) Color.GREEN else Color.RED
                        else -> Color.WHITE
                    }

                    cards_progressbar.visibility =
                        if (state as? ViewState.Loading<Boolean> != null) View.VISIBLE else View.GONE

                    setBackgroundColor(color)
                    setOnClickListener { onItemClick(card) }
                }
            })
        }
    }
}

private object DIFF_UTIL : DiffUtil.ItemCallback<LiveData<Pair<Card, ViewState<Boolean>>>>() {
    override fun areItemsTheSame(
        oldItem: LiveData<Pair<Card, ViewState<Boolean>>>,
        newItem: LiveData<Pair<Card, ViewState<Boolean>>>
    ): Boolean {
        return oldItem.value?.first?.id == newItem.value?.first?.id
    }

    override fun areContentsTheSame(
        oldItem: LiveData<Pair<Card, ViewState<Boolean>>>,
        newItem: LiveData<Pair<Card, ViewState<Boolean>>>
    ): Boolean {
        return oldItem.value?.second == newItem.value?.second
    }

}