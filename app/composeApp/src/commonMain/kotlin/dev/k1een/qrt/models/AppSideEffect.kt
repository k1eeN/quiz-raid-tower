package dev.k1een.qrt.models

sealed interface AppSideEffect {

    data object NavigateToHome : AppSideEffect
}
