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
import dev.k1een.qrt.feature.home.impl.resources.total_raids
import kotlin.math.roundToInt

internal class StatisticsUiMapper {

    operator fun invoke(state: StatisticsState): StatisticsUiState {
        val statistics = state.statistics
        val content = if (statistics == null || statistics.raidCount == 0) {
            StatisticsUiState.Content.Empty
        } else {
            StatisticsUiState.Content.Populated(
                metrics = listOf(
                    StatisticsUiState.MetricUiModel(
                        value = statistics.raidCount.toString(),
                        caption = Res.string.total_raids,
                        icon = QrtIcon.Swords,
                    ),
                    StatisticsUiState.MetricUiModel(
                        value = "${statistics.bestFloor} / 5",
                        caption = Res.string.best_floor,
                        icon = QrtIcon.Tower,
                    ),
                    StatisticsUiState.MetricUiModel(
                        value = statistics.correctAnswers.toString(),
                        caption = Res.string.correct_answers,
                        icon = QrtIcon.CheckCircle,
                    ),
                    StatisticsUiState.MetricUiModel(
                        value = accuracy(statistics.correctAnswers, statistics.answerCount),
                        caption = Res.string.accuracy,
                        icon = QrtIcon.Target,
                    ),
                ),
                categories = statistics.categories
                    .filter { category -> category.answerCount > 0 }
                    .map { category -> buildCategory(category) },
            )
        }

        return StatisticsUiState(content = content)
    }

    private fun buildCategory(state: StatisticsState.CategoryState): StatisticsUiState.CategoryUiModel {
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
        )
    }

    private fun accuracy(correct: Int, total: Int): String {
        return if (total > 0) {
            "${(correct * 100.0 / total).roundToInt()}%"
        } else {
            "—"
        }
    }
}
