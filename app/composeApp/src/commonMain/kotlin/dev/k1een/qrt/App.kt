package dev.k1een.qrt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.k1een.qrt.core.designsystem.components.QrtErrorScreen
import dev.k1een.qrt.core.designsystem.components.QrtLoadingScreen
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import dev.k1een.qrt.core.navigation.NavigationHost
import dev.k1een.qrt.feature.game.api.navigation.Battle
import dev.k1een.qrt.feature.game.api.navigation.FloorCompleted
import dev.k1een.qrt.feature.game.api.navigation.RaidResult
import dev.k1een.qrt.feature.game.api.navigation.SetupRaid
import dev.k1een.qrt.feature.game.impl.navigation.gameEntries
import dev.k1een.qrt.feature.home.api.navigation.Home
import dev.k1een.qrt.feature.home.api.navigation.Statistics
import dev.k1een.qrt.feature.home.impl.navigation.homeEntries
import dev.k1een.qrt.models.AppAction
import dev.k1een.qrt.models.AppSideEffect
import dev.k1een.qrt.models.AppUiState
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Home::class, Home.serializer())
            subclass(Statistics::class, Statistics.serializer())
            subclass(SetupRaid::class, SetupRaid.serializer())
            subclass(Battle::class, Battle.serializer())
            subclass(FloorCompleted::class, FloorCompleted.serializer())
            subclass(RaidResult::class, RaidResult.serializer())
        }
    }
}

@Composable
fun App() {
    val backStack = rememberNavBackStack(config, Home)
    val viewModel = koinViewModel<AppViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel, lifecycleOwner, backStack) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    AppSideEffect.NavigateToHome -> {
                        while (backStack.size > 1) backStack.removeLastOrNull()
                    }
                }
            }
        }
    }

    QrtTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (uiState.state) {
                AppUiState.State.Loading -> QrtLoadingScreen()
                AppUiState.State.Error -> QrtErrorScreen(
                    onRetry = { viewModel.onAction(AppAction.RetryClicked) },
                    onHome = { viewModel.onAction(AppAction.HomeClicked) },
                )

                AppUiState.State.Content -> NavigationHost(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        homeEntries(backStack)
                        gameEntries(backStack)
                    },
                )
            }
        }
    }
}
