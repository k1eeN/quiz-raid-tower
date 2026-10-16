package dev.k1een.qrt.core.navigation.di

import dev.k1een.qrt.core.navigation.Navigator
import org.koin.dsl.module

val navigationModule = module {
    factory { parameters -> Navigator(startDestination = parameters.get()) }
}
