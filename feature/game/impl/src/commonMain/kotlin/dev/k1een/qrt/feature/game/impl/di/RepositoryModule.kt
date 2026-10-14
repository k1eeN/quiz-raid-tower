package dev.k1een.qrt.feature.game.impl.di

import dev.k1een.qrt.feature.game.impl.data.repository.OpenTdbGameRepository
import dev.k1een.qrt.feature.game.impl.domain.repository.GameRepository
import org.koin.dsl.module

val gameRepositoryModule = module {
    single<GameRepository> {
        OpenTdbGameRepository(get(), get())
    }
}
