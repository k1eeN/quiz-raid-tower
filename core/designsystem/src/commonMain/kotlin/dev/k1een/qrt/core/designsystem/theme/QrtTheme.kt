package dev.k1een.qrt.core.designsystem.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.playfair_bold
import dev.k1een.qrt.core.designsystem.resources.playfair_regular
import dev.k1een.qrt.core.designsystem.resources.pt_serif_bold
import dev.k1een.qrt.core.designsystem.resources.pt_serif_regular
import org.jetbrains.compose.resources.Font

object QrtColors {
    val Background = Color(0xFF091521)
    val Ivory = Color(0xFFF5E8CE)
    val Muted = Color(0xFFB5C1CD)
    val Gold = Color(0xFFFFE0A0)
    val Rule = Color(0xFFBC8B42)
    val Teal = Color(0xFF1BCFC4)
    val Track = Color(0xFF394D5B)
    val Ink = Color(0xFF211706)
}

@Composable
fun QrtTheme(content: @Composable () -> Unit) {
    val display =
        FontFamily(
            Font(Res.font.playfair_regular, FontWeight.Normal),
            Font(Res.font.playfair_bold, FontWeight.Bold),
        )
    val body =
        FontFamily(
            Font(Res.font.pt_serif_regular, FontWeight.Normal),
            Font(Res.font.pt_serif_bold, FontWeight.Bold),
        )
    fun style(family: FontFamily, size: Int, line: Int, weight: FontWeight = FontWeight.Normal) =
        TextStyle(
            fontFamily = family,
            fontWeight = weight,
            fontSize = size.sp,
            lineHeight = line.sp,
        )
    MaterialTheme(
        colorScheme =
            darkColorScheme(
                primary = QrtColors.Gold,
                onPrimary = QrtColors.Ink,
                background = QrtColors.Background,
                onBackground = QrtColors.Ivory,
                surface = QrtColors.Background,
                onSurface = QrtColors.Ivory,
            ),
        typography =
            Typography(
                displayLarge = style(display, 40, 48),
                displayMedium = style(display, 30, 38),
                displaySmall = style(display, 28, 36),
                headlineLarge = style(display, 26, 34),
                headlineMedium = style(display, 25, 32),
                headlineSmall = style(display, 22, 30),
                titleLarge = style(display, 26, 34, FontWeight.Bold),
                bodyLarge = style(body, 16, 20),
                bodyMedium = style(body, 14, 18),
                bodySmall = style(body, 13, 17),
                labelLarge = style(body, 18, 24, FontWeight.Bold),
                labelMedium = style(body, 12, 16),
            ),
        content = {
            CompositionLocalProvider(LocalContentColor provides QrtColors.Ivory, content = content)
        },
    )
}
