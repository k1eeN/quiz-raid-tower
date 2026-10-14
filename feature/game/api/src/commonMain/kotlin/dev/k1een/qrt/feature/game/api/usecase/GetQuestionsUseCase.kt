package dev.k1een.qrt.feature.game.api.usecase

import dev.k1een.qrt.feature.game.api.models.Question

interface GetQuestionsUseCase {
    suspend operator fun invoke(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null,
        type: String? = null,
    ): List<Question>
}
