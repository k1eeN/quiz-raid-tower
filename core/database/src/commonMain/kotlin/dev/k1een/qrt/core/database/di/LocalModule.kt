package dev.k1een.qrt.core.database.di

import dev.k1een.qrt.core.database.data.local.SessionTokenStorage
import org.koin.dsl.module

val localModule = module {
    single { SessionTokenStorage() }
}
