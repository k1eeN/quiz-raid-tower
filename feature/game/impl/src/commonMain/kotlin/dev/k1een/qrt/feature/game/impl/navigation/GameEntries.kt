package dev.k1een.qrt.feature.game.impl.navigation

import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.k1een.qrt.feature.game.api.navigation.Battle
import dev.k1een.qrt.feature.game.api.navigation.FloorCompleted
import dev.k1een.qrt.feature.game.api.navigation.RaidResult
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.game.impl.presentation.ui.BattleScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.FloorCompletedScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.RaidResultScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.SetupRaidScreen

fun EntryProviderScope<NavKey>.gameEntries(backStack: NavBackStack<NavKey>) {
    entry<SetupRaid> {
        SetupRaidScreen(
            onNext = dropUnlessResumed {
                backStack[backStack.lastIndex] = Battle
            },
        )
    }
    entry<Battle> {
        BattleScreen(
            onNext = dropUnlessResumed {
                backStack[backStack.lastIndex] = FloorCompleted
            },
            onResult = dropUnlessResumed {
                backStack[backStack.lastIndex] = RaidResult
            },
        )
    }
    entry<FloorCompleted> {
        FloorCompletedScreen(
            onNextBattle = dropUnlessResumed {
                backStack[backStack.lastIndex] = Battle
            },
            onResult = dropUnlessResumed {
                backStack[backStack.lastIndex] = RaidResult
            },
        )
    }
    entry<RaidResult> {
        RaidResultScreen(
            onHome = dropUnlessResumed {
                while (backStack.size > 1) backStack.removeLastOrNull()
            },
            onNewRaid = dropUnlessResumed {
                backStack[backStack.lastIndex] = SetupRaid
            },
        )
    }
}
