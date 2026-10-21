package dev.k1een.qrt.di

import dev.k1een.qrt.AppViewModel
import dev.k1een.qrt.core.database.di.localModule
import dev.k1een.qrt.core.database.di.networkModule
import dev.k1een.qrt.core.database.di.repositoryModule
import dev.k1een.qrt.feature.game.impl.di.gameNetworkModule
import dev.k1een.qrt.feature.game.impl.di.gameRepositoryModule
import dev.k1een.qrt.feature.game.impl.di.gameUseCaseModule
import dev.k1een.qrt.feature.home.impl.di.homePresentationModule
import dev.k1een.qrt.mappers.AppUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    includes(networkModule, localModule, repositoryModule)
    includes(gameNetworkModule, gameRepositoryModule, gameUseCaseModule)
    includes(homePresentationModule)
    factory { AppUiMapper() }
    viewModel {
        AppViewModel(
            sessionTokenRepository = get(),
            uiMapper = get(),
        )
    }
}
