package dev.k1een.qrt.feature.home.impl.presentation.statistics.models

import dev.k1een.qrt.core.designsystem.components.QrtIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal data class StatisticsUiState(
    val background: DrawableResource,
    val content: Content,
) {
    sealed interface Content {

        data object Empty : Content

        data class Populated(
            val metrics: MetricsUiModel,
            val categories: List<CategoryUiModel>,
        ) : Content
    }

    data class MetricsUiModel(
        val totalRaids: MetricUiModel,
        val bestFloor: MetricUiModel,
        val correctAnswers: MetricUiModel,
        val accuracy: MetricUiModel,
    )

    data class MetricUiModel(
        val value: String,
        val caption: StringResource,
        val icon: QrtIcon,
    )

    data class CategoryUiModel(
        val title: StringResource,
        val value: String,
        val progress: Float,
        val icon: QrtIcon,
        val isDividerVisible: Boolean,
    )
}
