package dev.k1een.qrt.feature.game.impl.di

import dev.k1een.qrt.feature.game.impl.data.network.api.GameApiService
import org.koin.dsl.module

val gameNetworkModule = module {
    single { GameApiService(get()) }
}
