package pokemontcg.features.cards.di

import org.koin.core.module.Module

object CardsModule {
    fun getModules(): Array<Module> = arrayOf(
        dataModule,
        useCaseModule,
        uiModule
    )
}