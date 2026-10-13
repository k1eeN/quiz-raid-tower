package dev.k1een.qrt.core.database.data.network.api

import dev.k1een.qrt.core.database.data.network.models.TokenResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class OpenTdbApiService(private val client: HttpClient) {
    suspend fun requestSessionToken(): TokenResponseDto {
        return client.get("api_token.php") {
            parameter("command", "request")
        }.body()
    }
}
