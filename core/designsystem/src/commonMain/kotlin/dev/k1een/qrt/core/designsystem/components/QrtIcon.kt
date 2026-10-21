package dev.k1een.qrt.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.icon_arrow
import dev.k1een.qrt.core.designsystem.resources.icon_back
import dev.k1een.qrt.core.designsystem.resources.icon_book
import dev.k1een.qrt.core.designsystem.resources.icon_chart
import dev.k1een.qrt.core.designsystem.resources.icon_check_circle
import dev.k1een.qrt.core.designsystem.resources.icon_flask
import dev.k1een.qrt.core.designsystem.resources.icon_globe
import dev.k1een.qrt.core.designsystem.resources.icon_home
import dev.k1een.qrt.core.designsystem.resources.icon_retry
import dev.k1een.qrt.core.designsystem.resources.icon_swords
import dev.k1een.qrt.core.designsystem.resources.icon_target
import dev.k1een.qrt.core.designsystem.resources.icon_tower
import dev.k1een.qrt.core.designsystem.resources.icon_trophy
import org.jetbrains.compose.resources.painterResource

enum class QrtIcon {
    Arrow,
    Back,
    Chart,
    Tower,
    Trophy,
    Swords,
    CheckCircle,
    Target,
    Book,
    Flask,
    Globe,
    Home,
    Retry;

    internal val resource
        get() =
            when (this) {
                Arrow -> Res.drawable.icon_arrow
                Back -> Res.drawable.icon_back
                Chart -> Res.drawable.icon_chart
                Tower -> Res.drawable.icon_tower
                Trophy -> Res.drawable.icon_trophy
                Swords -> Res.drawable.icon_swords
                CheckCircle -> Res.drawable.icon_check_circle
                Target -> Res.drawable.icon_target
                Book -> Res.drawable.icon_book
                Flask -> Res.drawable.icon_flask
                Globe -> Res.drawable.icon_globe
                Home -> Res.drawable.icon_home
                Retry -> Res.drawable.icon_retry
            }
}

@Composable
fun QrtIconImage(
    icon: QrtIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) {
    Image(
        painter = painterResource(icon.resource),
        contentDescription = contentDescription,
        modifier = modifier,
        colorFilter = tint?.let { color -> ColorFilter.tint(color) },
    )
}
