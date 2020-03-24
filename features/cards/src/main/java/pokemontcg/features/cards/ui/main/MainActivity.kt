package pokemontcg.features.cards.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.cards_activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import pokemontcg.features.cards.R
import pokemontcg.libraries.ui_components.extensions.createLoadingDialog
import pokemontcg.libraries.ui_components.extensions.showMessage

class MainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()
    private val viewModel: MainViewModel by viewModel()

    private val loadingDialog by lazy {
        createLoadingDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_activity_main)

        setupView()
        setupObservers()

        viewModel.init()
    }

    private fun setupView() {
        rvCards.adapter = cardsAdapter
    }

    private fun setupObservers() {
        viewModel.cards.observe(this, Observer {
            cardsAdapter.submitList(it)
        })

        viewModel.showError.observe(this, Observer { showMessage(it) })

        viewModel.isLoading.observe(this, Observer {
            if (it == true) {
                loadingDialog.show()
            } else {
                loadingDialog.hide()
            }
        })
    }


}