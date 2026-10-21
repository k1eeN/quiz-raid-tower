package dev.k1een.qrt.feature.home.impl.presentation.home.models

internal data class HomeUiState(val records: RecordsUiModel) {
    data class RecordsUiModel(
        val bestFloor: String,
        val bestResult: String,
    )
}
