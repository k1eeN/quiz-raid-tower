package dev.k1een.qrt.feature.home.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dev.k1een.qrt.core.navigation.Navigator
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.home.api.navigation.Home
import dev.k1een.qrt.feature.home.api.navigation.Statistics
import dev.k1een.qrt.feature.home.impl.presentation.ui.HomeScreen
import dev.k1een.qrt.feature.home.impl.presentation.ui.StatisticsScreen

internal fun EntryProviderScope<NavKey>.homeEntries(navigator: Navigator) {
    entry<Home> {
        HomeScreen(
            onStartRaid = {
                navigator.navigate(SetupRaid)
            },
            onStatistics = {
                navigator.navigate(Statistics)
            },
        )
    }
    entry<Statistics> {
        StatisticsScreen(onBack = navigator::back)
    }
}
