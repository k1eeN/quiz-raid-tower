package dev.k1een.qrt.feature.game.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dev.k1een.qrt.core.navigation.Navigator
import dev.k1een.qrt.feature.game.api.navigation.Battle
import dev.k1een.qrt.feature.game.api.navigation.FloorCompleted
import dev.k1een.qrt.feature.game.api.navigation.RaidResult
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.game.impl.presentation.ui.BattleScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.FloorCompletedScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.RaidResultScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.SetupRaidScreen

internal fun EntryProviderScope<NavKey>.gameEntries(navigator: Navigator) {
    entry<SetupRaid> {
        SetupRaidScreen(onNext = {
            navigator.replace(Battle)
        })
    }
    entry<Battle> {
        BattleScreen(
            onNext = {
                navigator.replace(FloorCompleted)
            },
            onResult = {
                navigator.replace(RaidResult)
            },
        )
    }
    entry<FloorCompleted> {
        FloorCompletedScreen(
            onNextBattle = {
                navigator.replace(Battle)
            },
            onResult = {
                navigator.replace(RaidResult)
            },
        )
    }
    entry<RaidResult> {
        RaidResultScreen(
            onHome = navigator::popToRoot,
            onNewRaid = {
                navigator.replace(SetupRaid)
            },
        )
    }
}
