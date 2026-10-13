package dev.k1een.qrt.di

import dev.k1een.qrt.AppViewModel
import dev.k1een.qrt.core.database.di.localModule
import dev.k1een.qrt.core.database.di.networkModule
import dev.k1een.qrt.core.database.di.repositoryModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    includes(networkModule, localModule, repositoryModule)
    viewModel { AppViewModel(get()) }
}
