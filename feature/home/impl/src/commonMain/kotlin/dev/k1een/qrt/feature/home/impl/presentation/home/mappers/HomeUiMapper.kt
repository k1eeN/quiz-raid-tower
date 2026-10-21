package dev.k1een.qrt.feature.home.impl.presentation.home.mappers

import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeState
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeUiState

internal class HomeUiMapper {

    operator fun invoke(state: HomeState): HomeUiState {
        val records = state.records
        val bestFloor = records?.let { records -> "${records.bestFloor} / 5" }
        val bestResult = records
            ?.takeIf { records -> records.bestResultAnswers > 0 }
            ?.let { records ->
                "${records.bestResultCorrect} / ${records.bestResultAnswers}"
            }

        return HomeUiState(
            records = HomeUiState.RecordsUiModel(
                bestFloor = bestFloor ?: "—",
                bestResult = bestResult ?: "—",
            ),
        )
    }
}
