package dev.k1een.qrt.feature.game.impl.di

import dev.k1een.qrt.feature.game.impl.presentation.setup.mappers.SetupRaidUiMapper
import dev.k1een.qrt.feature.game.impl.presentation.setup.viewmodels.SetupRaidViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gamePresentationModule = module {
    factory { SetupRaidUiMapper() }
    viewModel {
        SetupRaidViewModel(
            getCategoriesUseCase = get(),
            uiMapper = get(),
        )
    }
}
