package dev.k1een.qrt.feature.game.impl.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.k1een.qrt.feature.game.api.navigation.Battle
import dev.k1een.qrt.feature.game.api.navigation.FloorCompleted
import dev.k1een.qrt.feature.game.api.navigation.RaidResult
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidSideEffect
import dev.k1een.qrt.feature.game.impl.presentation.setup.ui.SetupRaidScreen
import dev.k1een.qrt.feature.game.impl.presentation.setup.viewmodels.SetupRaidViewModel
import dev.k1een.qrt.feature.game.impl.presentation.ui.BattleScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.FloorCompletedScreen
import dev.k1een.qrt.feature.game.impl.presentation.ui.RaidResultScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.gameEntries(backStack: NavBackStack<NavKey>) {
    entry<SetupRaid> {
        val viewModel = koinViewModel<SetupRaidViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(viewModel, lifecycleOwner, backStack) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect { sideEffect ->
                    if (backStack.lastOrNull() == SetupRaid) {
                        when (sideEffect) {
                            SetupRaidSideEffect.GoBack -> backStack.removeLastOrNull()
                            SetupRaidSideEffect.GoHome -> while (backStack.size > 1) backStack.removeLastOrNull()
                            is SetupRaidSideEffect.StartRaid -> backStack[backStack.lastIndex] = Battle(
                                categoryId = sideEffect.categoryId,
                                difficulty = sideEffect.difficulty,
                            )
                        }
                    }
                }
            }
        }

        SetupRaidScreen(
            state = uiState,
            onAction = viewModel::onAction,
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
                backStack[backStack.lastIndex] = Battle()
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
