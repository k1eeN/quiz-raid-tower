package dev.k1een.qrt.feature.home.impl.di

import dev.k1een.qrt.core.navigation.EntryInstaller
import dev.k1een.qrt.feature.home.impl.navigation.homeEntries
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeNavigationModule = module {
    single<EntryInstaller>(named("homeEntries")) {
        { navigator -> homeEntries(navigator) }
    }
}
