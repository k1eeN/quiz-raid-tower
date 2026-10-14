package dev.k1een.qrt

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<AppViewModel>()
    Text(
        text = viewModel.text,
        modifier = Modifier.safeContentPadding().verticalScroll(rememberScrollState()),
    )
}
