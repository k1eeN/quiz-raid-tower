package dev.k1een.qrt.mappers

import dev.k1een.qrt.models.AppState
import dev.k1een.qrt.models.AppUiState

internal class AppUiMapper {

    operator fun invoke(state: AppState): AppUiState {
        return AppUiState(
            state = when (state.state) {
                AppState.State.Loading -> AppUiState.State.Loading
                AppState.State.Error -> AppUiState.State.Error
                AppState.State.Content -> AppUiState.State.Content
            },
        )
    }
}
