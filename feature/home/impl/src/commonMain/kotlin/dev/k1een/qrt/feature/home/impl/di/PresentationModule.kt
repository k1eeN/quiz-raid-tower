package dev.k1een.qrt.feature.home.impl.di

import dev.k1een.qrt.feature.home.impl.presentation.home.mappers.HomeUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.home.viewmodels.HomeViewModel
import dev.k1een.qrt.feature.home.impl.presentation.statistics.mappers.StatisticsUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.statistics.viewmodels.StatisticsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {
    factory { HomeUiMapper() }
    factory { StatisticsUiMapper() }
    viewModel { HomeViewModel(uiMapper = get()) }
    viewModel { StatisticsViewModel(uiMapper = get()) }
}
