package dev.k1een.qrt.feature.game.impl.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class QuestionsResponseDto(
    @SerialName("response_code") val responseCode: Int,
    @SerialName("results") val results: List<QuestionDto>,
)
