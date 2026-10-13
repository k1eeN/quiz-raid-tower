package dev.k1een.qrt.core.database.di

import dev.k1een.qrt.core.database.data.repository.OpenTdbSessionTokenRepository
import dev.k1een.qrt.core.database.domain.repository.SessionTokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SessionTokenRepository> { OpenTdbSessionTokenRepository(get(), get()) }
}
