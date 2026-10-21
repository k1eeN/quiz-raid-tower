package dev.k1een.qrt.feature.home.impl.navigation

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
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.home.api.navigation.Home
import dev.k1een.qrt.feature.home.api.navigation.Statistics
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeAction
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeSideEffect
import dev.k1een.qrt.feature.home.impl.presentation.home.ui.HomeScreen
import dev.k1een.qrt.feature.home.impl.presentation.home.viewmodels.HomeViewModel
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsAction
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsSideEffect
import dev.k1een.qrt.feature.home.impl.presentation.statistics.ui.StatisticsScreen
import dev.k1een.qrt.feature.home.impl.presentation.statistics.viewmodels.StatisticsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.homeEntries(backStack: NavBackStack<NavKey>) {
    entry<Home> {
        val viewModel = koinViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(viewModel, lifecycleOwner, backStack) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect { sideEffect ->
                    if (backStack.lastOrNull() == Home) {
                        when (sideEffect) {
                            HomeSideEffect.OpenStatistics -> backStack.add(Statistics)
                            HomeSideEffect.StartRaid -> backStack.add(SetupRaid)
                        }
                    }
                }
            }
        }

        HomeScreen(
            state = uiState,
            onStartRaid = dropUnlessResumed {
                viewModel.onAction(HomeAction.StartRaidClicked)
            },
            onStatistics = dropUnlessResumed {
                viewModel.onAction(HomeAction.StatisticsClicked)
            },
        )
    }
    entry<Statistics> {
        val viewModel = koinViewModel<StatisticsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(viewModel, lifecycleOwner, backStack) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect { sideEffect ->
                    if (backStack.lastOrNull() == Statistics) {
                        when (sideEffect) {
                            StatisticsSideEffect.GoHome -> backStack.removeLastOrNull()
                            StatisticsSideEffect.StartRaid -> backStack[backStack.lastIndex] = SetupRaid
                        }
                    }
                }
            }
        }

        StatisticsScreen(
            state = uiState,
            onBack = dropUnlessResumed {
                viewModel.onAction(StatisticsAction.BackClicked)
            },
            onStartRaid = dropUnlessResumed {
                viewModel.onAction(StatisticsAction.StartRaidClicked)
            },
        )
    }
}
