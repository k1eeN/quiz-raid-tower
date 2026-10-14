package dev.k1een.qrt.core.database.data.repository

import dev.k1een.qrt.core.database.data.local.SessionTokenStorage
import dev.k1een.qrt.core.database.data.network.api.OpenTdbApiService
import dev.k1een.qrt.core.database.domain.exception.SessionTokenException
import dev.k1een.qrt.core.database.domain.repository.SessionTokenRepository

internal class OpenTdbSessionTokenRepository(
    private val apiService: OpenTdbApiService,
    private val storage: SessionTokenStorage,
) : SessionTokenRepository {
    override suspend fun requestAndStoreToken() {
        val response = apiService.requestSessionToken()
        val token = response.token

        if (response.responseCode != 0 || token.isNullOrBlank()) {
            throw SessionTokenException()
        }

        storage.token = token
    }
}
