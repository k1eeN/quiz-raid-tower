package dev.k1een.qrt.core.designsystem.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.error_environment
import dev.k1een.qrt.core.designsystem.resources.go_home
import dev.k1een.qrt.core.designsystem.resources.retry
import dev.k1een.qrt.core.designsystem.resources.start_error_copy
import dev.k1een.qrt.core.designsystem.resources.start_error_title
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtSizes
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun QrtErrorScreen(
    onRetry: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(Res.string.start_error_title),
    description: String = stringResource(Res.string.start_error_copy),
) {
    val safeTop = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()

    QrtScreen(
        background = Res.drawable.error_environment,
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
                Spacer(modifier = Modifier.height(maxOf(52.dp, safeTop)))
                QrtWordmark(
                    compact = true,
                    modifier = Modifier
                        .width(QrtSizes.CompactWordmarkWidth)
                        .height(QrtSizes.CompactWordmarkHeight),
                )
                Spacer(modifier = Modifier.weight(1f))
                ReadingScrim(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(horizontal = 24.dp),
                            style = MaterialTheme.typography.displayMedium,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = description,
                            modifier = Modifier.padding(horizontal = 24.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = QrtColors.Muted,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        QrtPrimaryButton(
                            text = stringResource(Res.string.retry),
                            onClick = onRetry,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.5.dp)
                                .height(67.dp),
                            leadingIcon = QrtIcon.Retry,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        QrtTextButton(
                            text = stringResource(Res.string.go_home),
                            icon = QrtIcon.Home,
                            onClick = onHome,
                            textStyle = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }
}

@Preview(name = "Error", widthDp = 393, heightDp = 852)
@Preview(name = "Error · 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun ErrorPreview() {
    QrtTheme {
        QrtErrorScreen(
            onRetry = {},
            onHome = {},
        )
    }
}
