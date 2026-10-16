package dev.k1een.qrt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.k1een.qrt.core.navigation.NavigationHost
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<AppViewModel>()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavigationHost(
                navigator = viewModel.navigator,
                modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            )
        }
    }
}
