package dev.k1een.qrt.feature.home.impl.presentation.home.models

internal data class HomeState(val records: RecordsState? = null) {
    data class RecordsState(
        val bestFloor: Int,
        val bestResultCorrect: Int,
        val bestResultAnswers: Int,
    )
}
