package pokemontcg.features.cards.di

import org.koin.dsl.bind
import org.koin.dsl.module
import pokemontcg.features.cards.data.CardsRepository
import pokemontcg.features.cards.data.network.CardsApi
import pokemontcg.features.cards.data.network.CardsNetworkRepository
import pokemontcg.libraries.network.ApiClientBuilder

internal val dataModule = module {
    single { ApiClientBuilder.createServiceApi(CardsApi::class.java) }
    factory { CardsNetworkRepository(api = get()) } bind CardsRepository::class
}