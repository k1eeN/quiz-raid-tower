package dev.k1een.qrt.models

data class AppState(val state: State = State.Loading) {
    sealed interface State {

        data object Loading : State

        data object Error : State

        data object Content : State
    }
}
