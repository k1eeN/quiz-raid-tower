package dev.k1een.qrt.feature.game.api.usecase

import dev.k1een.qrt.feature.game.api.models.Category

interface GetCategoriesUseCase {
    suspend operator fun invoke(): List<Category>
}
