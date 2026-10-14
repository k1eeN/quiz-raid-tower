package dev.k1een.qrt.feature.game.impl.domain.usecase

import dev.k1een.qrt.feature.game.api.models.Question
import dev.k1een.qrt.feature.game.api.usecase.GetQuestionsUseCase
import dev.k1een.qrt.feature.game.impl.data.mapper.toPublicQuestions
import dev.k1een.qrt.feature.game.impl.domain.repository.GameRepository

internal class GetQuestionsUseCaseImpl(
    private val repository: GameRepository,
) : GetQuestionsUseCase {
    override suspend fun invoke(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
    ): List<Question> {
        return repository.getQuestions(
            amount = amount,
            category = category,
            difficulty = difficulty,
            type = type,
        ).toPublicQuestions()
    }
}
