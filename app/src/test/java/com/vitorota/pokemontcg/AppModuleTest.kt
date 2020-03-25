package com.vitorota.pokemontcg

import com.vitorota.pokemontcg.di.AppModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

internal class AppModuleTest : KoinTest {
    @Test
    fun `check module`() {
        koinApplication {
            modules(AppModule.getModules())
        }.checkModules()
    }
}