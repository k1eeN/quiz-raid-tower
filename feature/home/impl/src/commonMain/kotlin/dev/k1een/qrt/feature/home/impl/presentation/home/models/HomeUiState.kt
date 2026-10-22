package dev.k1een.qrt.feature.home.impl.presentation.home.models

import dev.k1een.qrt.core.designsystem.components.QrtIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal data class HomeUiState(
    val background: DrawableResource,
    val records: RecordsUiModel,
) {
    data class RecordsUiModel(
        val bestFloor: RecordUiModel,
        val bestResult: RecordUiModel,
    )

    data class RecordUiModel(
        val value: String,
        val caption: StringResource,
        val icon: QrtIcon,
        val detail: StringResource? = null,
    )
}
