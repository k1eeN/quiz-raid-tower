package dev.k1een.qrt.feature.game.impl.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CategoriesResponseDto(
    @SerialName("trivia_categories") val triviaCategories: List<CategoryDto>,
)
