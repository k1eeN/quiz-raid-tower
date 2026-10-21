package dev.k1een.qrt.feature.home.impl.presentation.home.models

internal sealed interface HomeSideEffect {
    data object OpenStatistics : HomeSideEffect

    data object StartRaid : HomeSideEffect
}
