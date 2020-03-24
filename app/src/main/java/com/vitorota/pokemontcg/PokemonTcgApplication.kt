package com.vitorota.pokemontcg

import android.app.Application
import com.vitorota.pokemontcg.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonTcgApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@PokemonTcgApplication)
            modules(AppModule.getModules())
        }
    }
}