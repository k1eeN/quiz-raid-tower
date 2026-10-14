package dev.k1een.qrt.feature.game.impl.domain.model

internal data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
)
