package dev.k1een.qrt.feature.home.impl.presentation.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.core.designsystem.components.QrtIconImage
import dev.k1een.qrt.core.designsystem.components.QrtPrimaryButton
import dev.k1een.qrt.core.designsystem.components.QrtScreen
import dev.k1een.qrt.core.designsystem.components.QrtTextButton
import dev.k1een.qrt.core.designsystem.components.QrtWordmark
import dev.k1een.qrt.core.designsystem.components.ReadingScrim
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import dev.k1een.qrt.feature.home.impl.presentation.home.mappers.HomeUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeState
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeUiState
import dev.k1een.qrt.feature.home.impl.resources.Res
import dev.k1een.qrt.feature.home.impl.resources.start_raid
import dev.k1een.qrt.feature.home.impl.resources.statistics
import org.jetbrains.compose.resources.stringResource

private val LogoMinTopPadding = 57.dp
private val LogoTopInset = 9.dp
private val WordmarkMaxWidth = 301.dp
private val WordmarkHeight = 104.dp
private val RecordIconSize = 30.dp
private val RecordDividerWidth = .7.dp

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    onStartRaid: () -> Unit,
    onStatistics: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val safeTop = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()

    QrtScreen(
        background = state.background,
        modifier = modifier,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .heightIn(min = maxHeight),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(maxOf(LogoMinTopPadding, safeTop + LogoTopInset)))
                QrtWordmark(
                    compact = false,
                    modifier = Modifier
                        .widthIn(max = WordmarkMaxWidth)
                        .fillMaxWidth()
                        .height(WordmarkHeight),
                )
                Spacer(modifier = Modifier.weight(1f))
                ReadingScrim(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 13.dp, bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        HomeRecords(
                            state = state.records,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        QrtPrimaryButton(
                            text = stringResource(Res.string.start_raid),
                            onClick = onStartRaid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.5.dp)
                                .height(66.dp),
                            trailingArrow = true,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        QrtTextButton(
                            text = stringResource(Res.string.statistics),
                            icon = QrtIcon.Chart,
                            onClick = onStatistics,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeRecords(
    state: HomeUiState.RecordsUiModel,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.heightIn(min = 71.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Record(
                state = state.bestFloor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 19.dp),
            )
            Record(
                state = state.bestResult,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.5.dp),
            )
        }
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .width(8.dp)
                .height(60.dp),
        ) {
            val x = size.width / 2
            drawLine(
                color = QrtColors.Rule,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = RecordDividerWidth.toPx(),
            )
            val y = 25.dp.toPx()
            val diamond = Path().apply {
                moveTo(x, y - 4.dp.toPx())
                lineTo(x + 3.dp.toPx(), y)
                lineTo(x, y + 4.dp.toPx())
                lineTo(x - 3.dp.toPx(), y)
                close()
            }
            drawPath(
                path = diamond,
                color = QrtColors.Gold,
                style = Stroke(width = .9.dp.toPx()),
            )
        }
    }
}

@Composable
private fun Record(
    state: HomeUiState.RecordUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        QrtIconImage(
            icon = state.icon,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 4.dp)
                .size(RecordIconSize),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = state.value, style = MaterialTheme.typography.displaySmall)
            Text(text = stringResource(state.caption), style = MaterialTheme.typography.bodySmall)
            state.detail?.let { detail ->
                Text(
                    text = stringResource(detail),
                    style = MaterialTheme.typography.labelMedium,
                    color = QrtColors.Muted,
                )
            }
        }
    }
}

@Preview(name = "Home · records", widthDp = 393, heightDp = 852)
@Composable
private fun HomeRecordsPreview() {
    QrtTheme {
        HomeScreen(
            state = HomeUiMapper()(
                HomeState(
                    records = HomeState.RecordsState(
                        bestFloor = 5,
                        bestResultCorrect = 17,
                        bestResultAnswers = 17,
                    ),
                ),
            ),
            onStartRaid = {},
            onStatistics = {},
        )
    }
}

@Preview(name = "Home · empty", widthDp = 393, heightDp = 852)
@Preview(name = "Home · 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun HomeEmptyPreview() {
    QrtTheme {
        HomeScreen(
            state = HomeUiMapper()(HomeState()),
            onStartRaid = {},
            onStatistics = {},
        )
    }
}
