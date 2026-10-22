package dev.k1een.qrt.feature.game.impl.presentation.setup.models

import androidx.compose.ui.graphics.Color
import dev.k1een.qrt.core.designsystem.components.QrtIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal data class SetupRaidUiState(val state: State) {
    sealed interface State {

        data object Loading : State

        data class Error(
            val title: StringResource,
            val description: StringResource,
        ) : State

        data class Content(
            val background: DrawableResource,
            val allTopics: AllTopicsUiModel,
            val categories: List<CategoryUiModel>,
            val difficulties: List<DifficultyUiModel>,
        ) : State
    }

    data class AllTopicsUiModel(
        val title: StringResource,
        val icon: QrtIcon,
        val isSelected: Boolean,
    )

    data class CategoryUiModel(
        val id: Int,
        val name: String,
        val icon: QrtIcon,
        val isSelected: Boolean,
    )

    data class DifficultyUiModel(
        val difficulty: SetupRaidState.Difficulty,
        val title: StringResource,
        val icon: QrtIcon,
        val color: Color,
        val isSelected: Boolean,
    )
}
