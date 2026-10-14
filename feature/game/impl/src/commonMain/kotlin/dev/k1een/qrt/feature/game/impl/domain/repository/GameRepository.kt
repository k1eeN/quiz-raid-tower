package dev.k1een.qrt.feature.game.impl.domain.repository

import dev.k1een.qrt.feature.game.impl.domain.model.Category
import dev.k1een.qrt.feature.game.impl.domain.model.Question

internal interface GameRepository {
    suspend fun getCategories(): List<Category>

    suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
    ): List<Question>
}
