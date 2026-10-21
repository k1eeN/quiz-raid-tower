package dev.k1een.qrt.core.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.loading_environment
import dev.k1een.qrt.core.designsystem.resources.preparing_copy
import dev.k1een.qrt.core.designsystem.resources.preparing_title
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun QrtLoadingScreen(modifier: Modifier = Modifier) {
    val safeTop = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()

    QrtScreen(
        background = Res.drawable.loading_environment,
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
                Spacer(modifier = Modifier.height(maxOf(69.dp, safeTop + 17.dp)))
                QrtWordmark(
                    compact = true,
                    modifier = Modifier
                        .width(87.dp)
                        .height(47.45.dp),
                )
                Spacer(modifier = Modifier.height(115.55.dp))
                TowerAnimation(
                    modifier = Modifier
                        .width(190.dp)
                        .height(262.dp),
                )
                ReadingScrim(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(Res.string.preparing_title),
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .paddingFromBaseline(top = 80.dp),
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(Res.string.preparing_copy),
                            style = MaterialTheme.typography.bodyLarge,
                            color = QrtColors.Muted,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LoadingDiamonds(
                            modifier = Modifier
                                .width(46.dp)
                                .height(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingDiamonds(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        for (center in listOf(3.dp.toPx(), 23.dp.toPx(), 43.dp.toPx())) {
            val diamond = Path().apply {
                moveTo(center, 0f)
                lineTo(center + 3.dp.toPx(), size.height / 2)
                lineTo(center, size.height)
                lineTo(center - 3.dp.toPx(), size.height / 2)
                close()
            }
            drawPath(path = diamond, color = QrtColors.Gold)
            drawPath(
                path = diamond,
                color = QrtColors.Rule,
                style = Stroke(width = .6.dp.toPx()),
            )
        }
    }
}

@Preview(name = "Loading", widthDp = 393, heightDp = 852)
@Preview(name = "Loading · 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun LoadingPreview() {
    QrtTheme {
        QrtLoadingScreen()
    }
}
