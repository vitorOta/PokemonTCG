package pokemontcg.features.cards.usecase

import pokemontcg.features.cards.data.CardsRepository
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.UseCase

internal class ListCardsUseCase(private val repo: CardsRepository) :
    UseCase<Unit?, List<Card>> {

    override suspend fun execute(param: Unit?): List<Card> {
        return repo.listCards()
    }
}