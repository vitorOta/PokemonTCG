package pokemontcg.features.cards.di

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

internal class CardsModuleTest : KoinTest {

    @Test
    fun `check module`() {
        koinApplication {
            modules(*CardsModule.getModules())
        }.checkModules()
    }
}