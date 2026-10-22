package dev.k1een.qrt.feature.game.impl.presentation.setup.models

import dev.k1een.qrt.feature.game.api.models.Category

internal data class SetupRaidState(
    val state: State = State.Loading,
    val selectedCategoryId: Int? = null,
    val difficulty: Difficulty = Difficulty.Medium,
) {
    sealed interface State {

        data object Loading : State

        data object Error : State

        data class Content(
            val categories: List<Category>,
        ) : State
    }

    enum class Difficulty(val apiValue: String) {
        Easy("easy"),
        Medium("medium"),
        Hard("hard"),
    }
}
