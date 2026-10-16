package dev.k1een.qrt.feature.game.impl.di

import dev.k1een.qrt.core.navigation.EntryInstaller
import dev.k1een.qrt.feature.game.impl.navigation.gameEntries
import org.koin.core.qualifier.named
import org.koin.dsl.module

val gameNavigationModule = module {
    single<EntryInstaller>(named("gameEntries")) {
        { navigator -> gameEntries(navigator) }
    }
}
