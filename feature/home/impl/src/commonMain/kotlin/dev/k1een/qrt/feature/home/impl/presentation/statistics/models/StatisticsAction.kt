package dev.k1een.qrt.feature.home.impl.presentation.statistics.models

internal sealed interface StatisticsAction {
    data object BackClicked : StatisticsAction

    data object StartRaidClicked : StatisticsAction
}
