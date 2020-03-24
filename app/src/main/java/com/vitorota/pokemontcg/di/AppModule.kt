package com.vitorota.pokemontcg.di

import pokemontcg.features.cards.di.CardsModule

object AppModule {
    fun getModules() = listOf(
        *CardsModule.getModules()
    )
}