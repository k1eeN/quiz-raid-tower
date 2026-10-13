package dev.k1een.qrt.core.database.domain.repository

interface SessionTokenRepository {
    suspend fun requestAndStoreToken()
}
