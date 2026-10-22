package dev.k1een.qrt.feature.game.impl.presentation.setup.models

internal sealed interface SetupRaidAction {

    data object Load : SetupRaidAction

    data object RetryClicked : SetupRaidAction

    data class CategoryClicked(val id: Int?) : SetupRaidAction

    data class DifficultyClicked(val difficulty: SetupRaidState.Difficulty) : SetupRaidAction

    data object StartRaidClicked : SetupRaidAction

    data object BackClicked : SetupRaidAction

    data object HomeClicked : SetupRaidAction
}
