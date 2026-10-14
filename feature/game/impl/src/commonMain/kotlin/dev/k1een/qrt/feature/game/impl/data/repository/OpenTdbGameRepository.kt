package dev.k1een.qrt.feature.game.impl.data.repository

import dev.k1een.qrt.core.database.data.local.SessionTokenStorage
import dev.k1een.qrt.feature.game.impl.data.mapper.toDomain
import dev.k1een.qrt.feature.game.impl.data.network.api.GameApiService
import dev.k1een.qrt.feature.game.impl.domain.exception.GameRequestException
import dev.k1een.qrt.feature.game.impl.domain.model.Category
import dev.k1een.qrt.feature.game.impl.domain.model.Question
import dev.k1een.qrt.feature.game.impl.domain.repository.GameRepository

internal class OpenTdbGameRepository(
    private val apiService: GameApiService,
    private val storage: SessionTokenStorage,
) : GameRepository {
    override suspend fun getCategories(): List<Category> {
        val response = apiService.getCategories()

        return response.triviaCategories.map { categoryDto ->
            categoryDto.toDomain()
        }
    }

    override suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
    ): List<Question> {
        val token = storage.token ?: throw GameRequestException()

        val response = apiService.getQuestions(
            amount = amount,
            category = category,
            difficulty = difficulty,
            type = type,
            token = token,
        )

        if (response.responseCode != 0) {
            throw GameRequestException()
        }
        return response.results.map { questionDto ->
            questionDto.toDomain()
        }
    }
}
