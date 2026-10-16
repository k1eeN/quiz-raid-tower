package dev.k1een.qrt.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.getKoin

@Composable
fun NavigationHost(
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val koin = getKoin()
    val entryInstallers = remember(koin) { koin.getAll<EntryInstaller>() }
    NavDisplay(
        backStack = navigator.backStack,
        modifier = modifier,
        onBack = navigator::back,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entryInstallers.forEach { installer -> installer(navigator) }
        },
    )
}
