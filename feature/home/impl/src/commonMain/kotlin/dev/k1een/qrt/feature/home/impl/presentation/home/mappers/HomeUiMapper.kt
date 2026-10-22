package dev.k1een.qrt.feature.home.impl.presentation.home.mappers

import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeState
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeUiState
import dev.k1een.qrt.feature.home.impl.resources.Res
import dev.k1een.qrt.feature.home.impl.resources.best_floor
import dev.k1een.qrt.feature.home.impl.resources.best_result
import dev.k1een.qrt.feature.home.impl.resources.correct_answers_detail
import dev.k1een.qrt.feature.home.impl.resources.home_environment

internal class HomeUiMapper {

    operator fun invoke(state: HomeState): HomeUiState {
        val records = state.records
        val bestFloor = records?.let { records -> "${records.bestFloor} / $TOTAL_FLOORS" }
        val bestResult = records
            ?.takeIf { records -> records.bestResultAnswers > 0 }
            ?.let { records ->
                "${records.bestResultCorrect} / ${records.bestResultAnswers}"
            }

        return HomeUiState(
            background = Res.drawable.home_environment,
            records = HomeUiState.RecordsUiModel(
                bestFloor = HomeUiState.RecordUiModel(
                    value = bestFloor ?: EMPTY_VALUE,
                    caption = Res.string.best_floor,
                    icon = QrtIcon.Tower,
                ),
                bestResult = HomeUiState.RecordUiModel(
                    value = bestResult ?: EMPTY_VALUE,
                    caption = Res.string.best_result,
                    icon = QrtIcon.Trophy,
                    detail = Res.string.correct_answers_detail,
                ),
            ),
        )
    }

    private companion object {
        const val TOTAL_FLOORS = 5
        const val EMPTY_VALUE = "—"
    }
}
