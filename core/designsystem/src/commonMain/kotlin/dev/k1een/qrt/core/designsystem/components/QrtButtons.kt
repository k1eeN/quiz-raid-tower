package dev.k1een.qrt.core.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.button_preview_label
import dev.k1een.qrt.core.designsystem.resources.go_home
import dev.k1een.qrt.core.designsystem.resources.primary_gold_plate
import dev.k1een.qrt.core.designsystem.resources.retry
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtSizes
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.stringResource

private const val DISABLED_ALPHA = .55f
private const val PLATE_SOURCE_LEFT = 44
private const val PLATE_SOURCE_TOP = 32
private const val PLATE_SOURCE_WIDTH = 1292
private const val PLATE_SOURCE_HEIGHT = 192
private val GoldFrame = Brush.verticalGradient(
    0f to Color(0xFFFFE9B0),
    .16f to Color(0xFFF6C966),
    .38f to Color(0xFFB87523),
    .62f to Color(0xFFF6CB66),
    .82f to Color(0xFF7D4917),
    1f to Color(0xFFFFE6A5),
)
private val OuterStroke = Color(0xFF5F3B14)
private val InnerHighlight = Color(0xFFFFE9A5)
private val FaceStroke = Color(0xFF895717)
private val EdgeHighlight = Color(0xCCFFE0A0)
private val FaceInset = 6.dp

@Composable
fun QrtPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: QrtIcon? = null,
    trailingArrow: Boolean = false,
) {
    val material = imageResource(Res.drawable.primary_gold_plate)
    Box(
        modifier = modifier
            .heightIn(min = 56.dp)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .clip(CutCornerShape(10.dp))
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            fun outline(inset: Float, cut: Float) =
                Path().apply {
                    val left = inset
                    val top = inset
                    val right = size.width - inset
                    val bottom = size.height - inset
                    moveTo(left + cut, top)
                    lineTo(right - cut, top)
                    lineTo(right, top + cut)
                    lineTo(right, bottom - cut)
                    lineTo(right - cut, bottom)
                    lineTo(left + cut, bottom)
                    lineTo(left, bottom - cut)
                    lineTo(left, top + cut)
                    close()
                }
            drawPath(path = outline(0f, 10.dp.toPx()), brush = GoldFrame)
            drawPath(
                path = outline(.5.dp.toPx(), 10.dp.toPx()),
                color = OuterStroke,
                style = Stroke(width = 1.dp.toPx()),
            )
            val face = outline(FaceInset.toPx(), 4.dp.toPx())
            clipPath(face) {
                val ratio = (size.width - FaceInset.toPx() * 2) / (size.height - FaceInset.toPx() * 2)
                val sourceWidth = minOf(PLATE_SOURCE_WIDTH.toFloat(), PLATE_SOURCE_HEIGHT * ratio)
                val sourceHeight = minOf(PLATE_SOURCE_HEIGHT.toFloat(), PLATE_SOURCE_WIDTH / ratio)
                drawImage(
                    image = material,
                    srcOffset =
                        IntOffset(
                            (PLATE_SOURCE_LEFT + (PLATE_SOURCE_WIDTH - sourceWidth) / 2).toInt(),
                            (PLATE_SOURCE_TOP + (PLATE_SOURCE_HEIGHT - sourceHeight) / 2).toInt(),
                        ),
                    srcSize = IntSize(sourceWidth.toInt(), sourceHeight.toInt()),
                    dstOffset = IntOffset(FaceInset.roundToPx(), FaceInset.roundToPx()),
                    dstSize =
                        IntSize(
                            (size.width - FaceInset.toPx() * 2).toInt(),
                            (size.height - FaceInset.toPx() * 2).toInt(),
                        ),
                )
            }
            drawPath(
                path = outline(2.dp.toPx(), 8.dp.toPx()),
                color = InnerHighlight,
                style = Stroke(width = 1.dp.toPx()),
            )
            drawPath(path = face, color = FaceStroke, style = Stroke(width = .8.dp.toPx()))
            for (y in listOf(10.dp.toPx(), size.height - 10.dp.toPx())) {
                drawLine(
                    color = EdgeHighlight,
                    start = Offset(10.dp.toPx(), y),
                    end = Offset(size.width - 10.dp.toPx(), y),
                    strokeWidth = .7.dp.toPx(),
                )
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (leadingIcon != null) {
                QrtIconImage(
                    icon = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = QrtColors.Ink,
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                color = QrtColors.Ink,
            )
        }
        if (trailingArrow) {
            QrtIconImage(
                icon = QrtIcon.Arrow,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 44.dp)
                    .size(18.dp),
                tint = QrtColors.Ink,
            )
        }
    }
}

@Composable
fun QrtTextButton(
    text: String,
    icon: QrtIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
) {
    Row(
        modifier = modifier
            .heightIn(min = QrtSizes.MinTouchTarget)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        QrtIconImage(
            icon = icon,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
        )
        Text(text = text, style = textStyle)
    }
}

@Preview(name = "Buttons", widthDp = 393, heightDp = 270)
@Composable
private fun ButtonsPreview() {
    QrtTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QrtColors.Background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            QrtPrimaryButton(
                text = stringResource(Res.string.button_preview_label),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp),
                trailingArrow = true,
            )
            QrtPrimaryButton(
                text = stringResource(Res.string.retry),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(67.dp),
                leadingIcon = QrtIcon.Retry,
            )
            QrtPrimaryButton(
                text = stringResource(Res.string.button_preview_label),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = false,
                trailingArrow = true,
            )
        }
    }
}

@Preview(name = "Text button", widthDp = 200, heightDp = 80)
@Composable
private fun TextButtonPreview() {
    QrtTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(QrtColors.Background),
            contentAlignment = Alignment.Center,
        ) {
            QrtTextButton(
                text = stringResource(Res.string.go_home),
                icon = QrtIcon.Home,
                onClick = {},
            )
        }
    }
}
