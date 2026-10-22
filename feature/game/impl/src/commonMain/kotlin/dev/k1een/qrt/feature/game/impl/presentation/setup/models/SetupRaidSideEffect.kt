package dev.k1een.qrt.feature.game.impl.presentation.setup.models

internal sealed interface SetupRaidSideEffect {

    data object GoBack : SetupRaidSideEffect

    data object GoHome : SetupRaidSideEffect

    data class StartRaid(val categoryId: Int?, val difficulty: String) : SetupRaidSideEffect
}
