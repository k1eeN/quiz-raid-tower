package dev.k1een.qrt.feature.home.impl.presentation.home.models

internal sealed interface HomeAction {
    data object StatisticsClicked : HomeAction

    data object StartRaidClicked : HomeAction
}
