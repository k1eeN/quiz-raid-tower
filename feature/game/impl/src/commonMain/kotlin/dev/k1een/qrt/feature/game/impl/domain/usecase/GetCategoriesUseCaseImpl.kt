package dev.k1een.qrt.feature.game.impl.domain.usecase

import dev.k1een.qrt.feature.game.api.models.Category
import dev.k1een.qrt.feature.game.api.usecase.GetCategoriesUseCase
import dev.k1een.qrt.feature.game.impl.data.mapper.toPublicCategories
import dev.k1een.qrt.feature.game.impl.domain.repository.GameRepository

internal class GetCategoriesUseCaseImpl(
    private val repository: GameRepository,
) : GetCategoriesUseCase {
    override suspend fun invoke(): List<Category> {
        return repository.getCategories().toPublicCategories()
    }
}
