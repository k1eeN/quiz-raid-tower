package dev.k1een.qrt.feature.game.impl.presentation.setup.mappers

import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidState
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidState.Difficulty
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidUiState
import dev.k1een.qrt.feature.game.impl.resources.Res
import dev.k1een.qrt.feature.game.impl.resources.setup_all_topics
import dev.k1een.qrt.feature.game.impl.resources.setup_easy
import dev.k1een.qrt.feature.game.impl.resources.setup_environment
import dev.k1een.qrt.feature.game.impl.resources.setup_error_copy
import dev.k1een.qrt.feature.game.impl.resources.setup_error_title
import dev.k1een.qrt.feature.game.impl.resources.setup_hard
import dev.k1een.qrt.feature.game.impl.resources.setup_medium

internal class SetupRaidUiMapper {

    operator fun invoke(state: SetupRaidState): SetupRaidUiState {
        return SetupRaidUiState(
            state = when (val screenState = state.state) {
                SetupRaidState.State.Loading -> SetupRaidUiState.State.Loading
                SetupRaidState.State.Error -> SetupRaidUiState.State.Error(
                    title = Res.string.setup_error_title,
                    description = Res.string.setup_error_copy,
                )
                is SetupRaidState.State.Content -> buildContent(screenState, state)
            },
        )
    }

    private fun buildContent(
        content: SetupRaidState.State.Content,
        state: SetupRaidState,
    ): SetupRaidUiState.State.Content {
        return SetupRaidUiState.State.Content(
            background = Res.drawable.setup_environment,
            allTopics = SetupRaidUiState.AllTopicsUiModel(
                title = Res.string.setup_all_topics,
                icon = QrtIcon.All,
                isSelected = state.selectedCategoryId == null,
            ),
            categories = content.categories.map { category ->
                SetupRaidUiState.CategoryUiModel(
                    id = category.id,
                    name = category.name.substringAfter(CATEGORY_NAME_SEPARATOR),
                    icon = CategoryIcon.entries
                        .firstOrNull { categoryIcon -> categoryIcon.categoryId == category.id }
                        ?.icon
                        ?: QrtIcon.All,
                    isSelected = state.selectedCategoryId == category.id,
                )
            },
            difficulties = buildDifficulties(state.difficulty),
        )
    }

    private fun buildDifficulties(selected: Difficulty): List<SetupRaidUiState.DifficultyUiModel> {
        return listOf(
            SetupRaidUiState.DifficultyUiModel(
                difficulty = Difficulty.Easy,
                title = Res.string.setup_easy,
                icon = QrtIcon.Shield,
                color = QrtColors.Teal,
                isSelected = selected == Difficulty.Easy,
            ),
            SetupRaidUiState.DifficultyUiModel(
                difficulty = Difficulty.Medium,
                title = Res.string.setup_medium,
                icon = QrtIcon.Chart,
                color = QrtColors.Gold,
                isSelected = selected == Difficulty.Medium,
            ),
            SetupRaidUiState.DifficultyUiModel(
                difficulty = Difficulty.Hard,
                title = Res.string.setup_hard,
                icon = QrtIcon.Skull,
                color = QrtColors.Danger,
                isSelected = selected == Difficulty.Hard,
            ),
        )
    }

    private companion object {
        const val CATEGORY_NAME_SEPARATOR = ": "
    }

    private enum class CategoryIcon(val categoryId: Int, val icon: QrtIcon) {
        GeneralKnowledge(9, QrtIcon.Knowledge),
        Books(10, QrtIcon.Book),
        Film(11, QrtIcon.Film),
        Music(12, QrtIcon.Music),
        Theatre(13, QrtIcon.Theatre),
        Television(14, QrtIcon.Television),
        VideoGames(15, QrtIcon.Game),
        BoardGames(16, QrtIcon.BoardGames),
        ScienceAndNature(17, QrtIcon.Flask),
        Computers(18, QrtIcon.Computer),
        Mathematics(19, QrtIcon.Mathematics),
        Mythology(20, QrtIcon.Temple),
        Sports(21, QrtIcon.Sports),
        Geography(22, QrtIcon.Globe),
        History(23, QrtIcon.Book),
        Politics(24, QrtIcon.Scales),
        Art(25, QrtIcon.Palette),
        Celebrities(26, QrtIcon.Star),
        Animals(27, QrtIcon.Paw),
        Vehicles(28, QrtIcon.Car),
        Comics(29, QrtIcon.Comic),
        Gadgets(30, QrtIcon.Chip),
        Anime(31, QrtIcon.Anime),
        Cartoons(32, QrtIcon.Cartoon),
    }
}
