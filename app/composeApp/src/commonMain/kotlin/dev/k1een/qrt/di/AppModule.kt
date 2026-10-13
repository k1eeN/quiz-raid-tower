package dev.k1een.qrt.di

import dev.k1een.qrt.core.database.di.networkModule
import org.koin.dsl.module

internal val appModule = module {
    includes(networkModule)
}
