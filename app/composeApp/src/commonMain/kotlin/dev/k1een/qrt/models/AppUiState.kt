package dev.k1een.qrt.models

data class AppUiState(val state: State) {
    sealed interface State {

        data object Loading : State

        data object Error : State

        data object Content : State
    }
}
