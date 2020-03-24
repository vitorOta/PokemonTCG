package pokemontcg.features.cards.di

import org.koin.dsl.module
import pokemontcg.features.cards.usecase.ListCardsUseCase

internal val useCaseModule = module {
    factory { ListCardsUseCase(repo = get()) }
}