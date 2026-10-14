package dev.k1een.qrt.feature.game.impl.data.mapper

import dev.k1een.qrt.feature.game.api.models.Category as PublicCategory
import dev.k1een.qrt.feature.game.api.models.Question as PublicQuestion
import dev.k1een.qrt.feature.game.impl.data.network.models.CategoryDto
import dev.k1een.qrt.feature.game.impl.data.network.models.QuestionDto
import dev.k1een.qrt.feature.game.impl.domain.model.Category
import dev.k1een.qrt.feature.game.impl.domain.model.Question

internal fun CategoryDto.toDomain(): Category = Category(
    id = id,
    name = name,
)

internal fun QuestionDto.toDomain(): Question = Question(
    type = type,
    difficulty = difficulty,
    category = category,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
)

internal fun List<Category>.toPublicCategories(): List<PublicCategory> = map { category ->
    PublicCategory(
        id = category.id,
        name = category.name,
    )
}

internal fun List<Question>.toPublicQuestions(): List<PublicQuestion> = map { question ->
    PublicQuestion(
        type = question.type,
        difficulty = question.difficulty,
        category = question.category,
        question = question.question,
        correctAnswer = question.correctAnswer,
        incorrectAnswers = question.incorrectAnswers,
    )
}
