package dev.k1een.qrt.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.selection_stone_plate
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import org.jetbrains.compose.resources.painterResource

private val SelectedFrame = Brush.verticalGradient(
    listOf(Color(0xFFFFE9B0), Color(0xFFA76F24), QrtColors.Gold),
)
private val DefaultFrame = Brush.verticalGradient(
    listOf(Color(0xFF8FA3B4), Color(0xFF203346), Color(0xFF698094)),
)
private val SelectedOverlay = Color(0x443D2914)
private val DefaultOverlay = Color(0x22203548)
private val TileMinHeight = 72.dp
private val FrameShape = CutCornerShape(7.dp)
private val ContentShape = CutCornerShape(5.dp)

@Composable
fun QrtSelectionTile(
    text: String,
    icon: QrtIcon,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accent: Color = QrtColors.Muted,
) {
    Box(
        modifier = modifier
            .heightIn(min = TileMinHeight)
            .clip(FrameShape)
            .background(if (selected) SelectedFrame else DefaultFrame)
            .border(.8.dp, if (selected) QrtColors.Gold else accent, FrameShape)
            .selectable(selected = selected, role = Role.RadioButton, onClick = onClick)
            .padding(2.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(Res.drawable.selection_stone_plate),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(ContentShape),
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(ContentShape)
                .background(if (selected) SelectedOverlay else DefaultOverlay),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            QrtIconImage(
                icon = icon,
                contentDescription = null,
                modifier = Modifier.size(29.dp),
                tint = if (selected) null else accent,
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = if (selected) QrtColors.Gold else QrtColors.Ivory,
                textAlign = TextAlign.Center,
            )
        }
        if (selected) {
            QrtIconImage(
                icon = QrtIcon.Check,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(3.dp)
                    .size(12.dp),
            )
        }
    }
}

@Preview(name = "Selection tiles", widthDp = 393, heightDp = 140)
@Composable
private fun SelectionTilesPreview() {
    QrtTheme {
        Row(
            modifier = Modifier
                .background(QrtColors.Background)
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            QrtSelectionTile(
                text = "All topics",
                icon = QrtIcon.All,
                selected = true,
                onClick = {},
                modifier = Modifier.weight(1f),
            )
            QrtSelectionTile(
                text = "Easy",
                icon = QrtIcon.Shield,
                selected = false,
                onClick = {},
                modifier = Modifier.weight(1f),
                accent = QrtColors.Teal,
            )
            QrtSelectionTile(
                text = "Hard",
                icon = QrtIcon.Skull,
                selected = false,
                onClick = {},
                modifier = Modifier.weight(1f),
                accent = QrtColors.Danger,
            )
        }
    }
}
