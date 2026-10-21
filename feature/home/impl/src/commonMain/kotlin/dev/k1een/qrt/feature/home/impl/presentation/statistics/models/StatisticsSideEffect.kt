package dev.k1een.qrt.feature.home.impl.presentation.statistics.models

internal sealed interface StatisticsSideEffect {
    data object GoHome : StatisticsSideEffect

    data object StartRaid : StatisticsSideEffect
}
