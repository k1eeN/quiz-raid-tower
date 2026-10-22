package dev.k1een.qrt.feature.home.impl.presentation.statistics.mappers

import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsState
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsUiState
import dev.k1een.qrt.feature.home.impl.resources.Res
import dev.k1een.qrt.feature.home.impl.resources.accuracy
import dev.k1een.qrt.feature.home.impl.resources.best_floor
import dev.k1een.qrt.feature.home.impl.resources.correct_answers
import dev.k1een.qrt.feature.home.impl.resources.geography
import dev.k1een.qrt.feature.home.impl.resources.history
import dev.k1een.qrt.feature.home.impl.resources.science
import dev.k1een.qrt.feature.home.impl.resources.statistics_empty_environment
import dev.k1een.qrt.feature.home.impl.resources.statistics_environment
import dev.k1een.qrt.feature.home.impl.resources.total_raids
import kotlin.math.roundToInt

internal class StatisticsUiMapper {

    operator fun invoke(state: StatisticsState): StatisticsUiState {
        val statistics = state.statistics
        if (statistics == null || statistics.raidCount == 0) {
            return StatisticsUiState(
                background = Res.drawable.statistics_empty_environment,
                content = StatisticsUiState.Content.Empty,
            )
        }

        val categories = statistics.categories.filter { category -> category.answerCount > 0 }

        return StatisticsUiState(
            background = Res.drawable.statistics_environment,
            content = StatisticsUiState.Content.Populated(
                metrics = StatisticsUiState.MetricsUiModel(
                    totalRaids = StatisticsUiState.MetricUiModel(
                        value = statistics.raidCount.toString(),
                        caption = Res.string.total_raids,
                        icon = QrtIcon.Swords,
                    ),
                    bestFloor = StatisticsUiState.MetricUiModel(
                        value = "${statistics.bestFloor} / $TOTAL_FLOORS",
                        caption = Res.string.best_floor,
                        icon = QrtIcon.Tower,
                    ),
                    correctAnswers = StatisticsUiState.MetricUiModel(
                        value = statistics.correctAnswers.toString(),
                        caption = Res.string.correct_answers,
                        icon = QrtIcon.CheckCircle,
                    ),
                    accuracy = StatisticsUiState.MetricUiModel(
                        value = if (statistics.answerCount > 0) {
                            accuracy(statistics.correctAnswers, statistics.answerCount)
                        } else {
                            EMPTY_VALUE
                        },
                        caption = Res.string.accuracy,
                        icon = QrtIcon.Target,
                    ),
                ),
                categories = categories.mapIndexed { index, category ->
                    buildCategory(
                        state = category,
                        isDividerVisible = index < categories.lastIndex,
                    )
                },
            ),
        )
    }

    private fun buildCategory(
        state: StatisticsState.CategoryState,
        isDividerVisible: Boolean,
    ): StatisticsUiState.CategoryUiModel {
        val (title, icon) = when (state.category) {
            StatisticsState.Category.History -> Res.string.history to QrtIcon.Book
            StatisticsState.Category.Science -> Res.string.science to QrtIcon.Flask
            StatisticsState.Category.Geography -> Res.string.geography to QrtIcon.Globe
        }

        return StatisticsUiState.CategoryUiModel(
            title = title,
            value = accuracy(state.correctAnswers, state.answerCount),
            progress = state.correctAnswers.toFloat() / state.answerCount,
            icon = icon,
            isDividerVisible = isDividerVisible,
        )
    }

    private fun accuracy(correct: Int, total: Int): String {
        return "${(correct * PERCENT_MULTIPLIER / total).roundToInt()}%"
    }

    private companion object {
        const val TOTAL_FLOORS = 5
        const val EMPTY_VALUE = "—"
        const val PERCENT_MULTIPLIER = 100.0
    }
}
