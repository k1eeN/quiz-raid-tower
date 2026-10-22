package dev.k1een.qrt.feature.home.impl.presentation.statistics.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.core.designsystem.components.QrtIconImage
import dev.k1een.qrt.core.designsystem.components.QrtPrimaryButton
import dev.k1een.qrt.core.designsystem.components.QrtScreen
import dev.k1een.qrt.core.designsystem.components.ReadingScrim
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtSizes
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import dev.k1een.qrt.feature.home.impl.presentation.statistics.mappers.StatisticsUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsState
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsUiState
import dev.k1een.qrt.feature.home.impl.resources.Res
import dev.k1een.qrt.feature.home.impl.resources.back
import dev.k1een.qrt.feature.home.impl.resources.by_category
import dev.k1een.qrt.feature.home.impl.resources.empty_copy
import dev.k1een.qrt.feature.home.impl.resources.empty_title
import dev.k1een.qrt.feature.home.impl.resources.progress
import dev.k1een.qrt.feature.home.impl.resources.start_raid
import dev.k1een.qrt.feature.home.impl.resources.statistics
import dev.k1een.qrt.feature.home.impl.resources.statistics_footer
import org.jetbrains.compose.resources.stringResource

private val HeaderMinBaseline = 109.dp
private val HeaderTopInset = 48.dp
private val HeaderMinHeight = 136.dp
private val MetricsMinWidth = 290.dp
private val MetricsMaxTrailingSpace = 43.dp
private val MetricsFirstRowMinHeight = 94.dp
private val MetricsSecondRowMinHeight = 80.dp
private val CategoryRowHeight = 61.dp
private val DividerWidth = .7.dp

@Composable
internal fun StatisticsScreen(
    state: StatisticsUiState,
    onBack: () -> Unit,
    onStartRaid: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            ) {
                StatisticsHeader(onBack = onBack)
                when (val content = state.content) {
                    StatisticsUiState.Content.Empty -> {
                        Spacer(modifier = Modifier.weight(1f))
                        EmptyStatistics(onStartRaid = onStartRaid)
                        Spacer(modifier = Modifier.height(105.dp))
                    }

                    is StatisticsUiState.Content.Populated -> {
                        StatisticsContent(state = content)
                    }
                }
            }
        }
    }
}

@Composable
private fun StatisticsHeader(onBack: () -> Unit) {
    val safeTop = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()
    val titleBaseline = maxOf(HeaderMinBaseline, safeTop + HeaderTopInset)

    Box(modifier = Modifier.fillMaxWidth().heightIn(min = HeaderMinHeight)) {
        Box(
            modifier = Modifier
                .padding(start = 12.dp, top = maxOf(36.dp, safeTop))
                .size(QrtSizes.MinTouchTarget)
                .clickable(role = Role.Button, onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            QrtIconImage(
                icon = QrtIcon.Back,
                contentDescription = stringResource(Res.string.back),
                modifier = Modifier.size(QrtSizes.NavigationIcon),
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.statistics),
                modifier = Modifier.paddingFromBaseline(top = titleBaseline),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(2.dp))
            StatisticsOrnament(
                modifier = Modifier
                    .width(149.dp)
                    .height(8.dp),
            )
        }
    }
}

@Composable
private fun StatisticsOrnament(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val center = size.width / 2
        val y = size.height / 2
        drawLine(
            color = QrtColors.Rule,
            start = Offset(0f, y),
            end = Offset(center - 8.dp.toPx(), y),
            strokeWidth = DividerWidth.toPx(),
        )
        drawLine(
            color = QrtColors.Rule,
            start = Offset(center + 8.dp.toPx(), y),
            end = Offset(size.width, y),
            strokeWidth = DividerWidth.toPx(),
        )
        val diamond = Path().apply {
            moveTo(center, y - 3.5.dp.toPx())
            lineTo(center + 3.5.dp.toPx(), y)
            lineTo(center, y + 3.5.dp.toPx())
            lineTo(center - 3.5.dp.toPx(), y)
            close()
        }
        drawPath(
            path = diamond,
            color = QrtColors.Gold,
            style = Stroke(width = 1.3.dp.toPx()),
        )
    }
}

@Composable
private fun EmptyStatistics(onStartRaid: () -> Unit) {
    ReadingScrim(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.empty_title),
                modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(Res.string.empty_copy),
                modifier = Modifier.padding(horizontal = 24.dp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            QrtPrimaryButton(
                text = stringResource(Res.string.start_raid),
                onClick = onStartRaid,
                modifier = Modifier
                    .widthIn(max = 301.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                trailingArrow = true,
            )
        }
    }
}

@Composable
private fun StatisticsContent(state: StatisticsUiState.Content.Populated) {
    ReadingScrim(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        ) {
            Text(
                text = stringResource(Res.string.progress),
                modifier = Modifier.paddingFromBaseline(top = 131.dp),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            StatisticsMetrics(metrics = state.metrics)
            Text(
                text = stringResource(Res.string.by_category),
                modifier = Modifier.paddingFromBaseline(top = 48.dp),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(14.dp))
            state.categories.forEach { category ->
                CategoryRow(state = category)
            }
            Text(
                text = stringResource(Res.string.statistics_footer),
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 21.dp),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun StatisticsMetrics(metrics: StatisticsUiState.MetricsUiModel) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val trailingInset = (maxWidth - MetricsMinWidth).coerceIn(0.dp, MetricsMaxTrailingSpace) / 2
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Metric(
                    state = metrics.totalRaids,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = MetricsFirstRowMinHeight),
                )
                Metric(
                    state = metrics.bestFloor,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = MetricsFirstRowMinHeight)
                        .padding(start = trailingInset),
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Metric(
                    state = metrics.correctAnswers,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = MetricsSecondRowMinHeight),
                )
                Metric(
                    state = metrics.accuracy,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = MetricsSecondRowMinHeight)
                        .padding(start = trailingInset),
                )
            }
        }
        Canvas(modifier = Modifier.matchParentSize()) {
            drawLine(
                color = QrtColors.Rule,
                start = Offset(size.width / 2, 13.dp.toPx()),
                end = Offset(size.width / 2, size.height - 16.dp.toPx()),
                strokeWidth = DividerWidth.toPx(),
            )
            drawLine(
                color = QrtColors.Rule,
                start = Offset(0f, 89.dp.toPx()),
                end = Offset(size.width, 89.dp.toPx()),
                strokeWidth = DividerWidth.toPx(),
            )
        }
    }
}

@Composable
private fun Metric(
    state: StatisticsUiState.MetricUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(start = 10.dp),
    ) {
        QrtIconImage(
            icon = state.icon,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 23.dp)
                .size(32.dp),
        )
        Spacer(modifier = Modifier.width(18.dp))
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = state.value,
                modifier = Modifier.paddingFromBaseline(top = 48.dp),
                style = MaterialTheme.typography.displayLarge,
                color = QrtColors.Gold,
            )
            Text(
                text = stringResource(state.caption),
                modifier = Modifier.paddingFromBaseline(top = 70.dp),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun CategoryRow(state: StatisticsUiState.CategoryUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(CategoryRowHeight),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 2.dp),
        ) {
            QrtIconImage(
                icon = state.icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(28.dp),
            )
            Spacer(modifier = Modifier.width(24.dp))
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(state.title),
                    modifier = Modifier.paddingFromBaseline(top = 18.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 17.sp),
                )
                Text(
                    text = state.value,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .paddingFromBaseline(top = 25.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 19.sp),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, end = 60.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(QrtColors.Track),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(state.progress)
                            .background(QrtColors.Teal, RoundedCornerShape(2.dp)),
                    )
                }
            }
        }
        if (state.isDividerVisible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 50.dp)
                    .height(DividerWidth)
                    .background(QrtColors.Rule),
            )
        }
    }
}

@Preview(name = "Statistics · populated", widthDp = 393, heightDp = 852)
@Preview(name = "Statistics · 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun StatisticsPopulatedPreview() {
    val state = StatisticsState(
        statistics = StatisticsState.Statistics(
            raidCount = 24,
            bestFloor = 5,
            correctAnswers = 326,
            answerCount = 379,
            categories = listOf(
                StatisticsState.CategoryState(StatisticsState.Category.History, 90, 100),
                StatisticsState.CategoryState(StatisticsState.Category.Science, 86, 100),
                StatisticsState.CategoryState(StatisticsState.Category.Geography, 82, 100),
            ),
        ),
    )
    QrtTheme {
        StatisticsScreen(
            state = StatisticsUiMapper()(state),
            onBack = {},
            onStartRaid = {},
        )
    }
}

@Preview(name = "Statistics · empty", widthDp = 393, heightDp = 852)
@Preview(name = "Statistics · empty 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun StatisticsEmptyPreview() {
    QrtTheme {
        StatisticsScreen(
            state = StatisticsUiMapper()(StatisticsState()),
            onBack = {},
            onStartRaid = {},
        )
    }
}
