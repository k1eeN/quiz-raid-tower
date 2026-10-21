package dev.k1een.qrt.feature.home.impl.presentation.statistics.models

internal data class StatisticsState(val statistics: Statistics? = null) {
    data class Statistics(
        val raidCount: Int,
        val bestFloor: Int,
        val correctAnswers: Int,
        val answerCount: Int,
        val categories: List<CategoryState>,
    )

    data class CategoryState(
        val category: Category,
        val correctAnswers: Int,
        val answerCount: Int,
    )

    enum class Category {
        History,
        Science,
        Geography,
    }
}
