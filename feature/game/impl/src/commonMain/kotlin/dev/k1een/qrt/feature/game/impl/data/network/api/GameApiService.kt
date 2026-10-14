package dev.k1een.qrt.feature.game.impl.data.network.api

import dev.k1een.qrt.feature.game.impl.data.network.models.CategoriesResponseDto
import dev.k1een.qrt.feature.game.impl.data.network.models.QuestionsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class GameApiService(private val client: HttpClient) {
    suspend fun getCategories(): CategoriesResponseDto {
        return client.get("api_category.php").body()
    }

    suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
        token: String,
    ): QuestionsResponseDto {
        return client.get("api.php") {
            parameter("amount", amount)
            parameter("category", category)
            parameter("difficulty", difficulty)
            parameter("type", type)
            parameter("token", token)
        }.body()
    }
}
