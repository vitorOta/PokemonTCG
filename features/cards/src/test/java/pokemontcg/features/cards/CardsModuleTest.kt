package pokemontcg.features.cards

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import pokemontcg.features.cards.di.CardsModule

internal class CardsModuleTest : KoinTest {

    @Test
    fun `check module`() {
        koinApplication {
            modules(*CardsModule.getModules())
        }.checkModules()
    }
}