package dev.k1een.qrt.models

sealed interface AppAction {

    data object Load : AppAction

    data object HomeClicked : AppAction
}
