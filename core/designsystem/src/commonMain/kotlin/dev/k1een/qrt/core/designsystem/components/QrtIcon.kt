package dev.k1een.qrt.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import dev.k1een.qrt.core.designsystem.resources.Res
import dev.k1een.qrt.core.designsystem.resources.icon_all
import dev.k1een.qrt.core.designsystem.resources.icon_anime
import dev.k1een.qrt.core.designsystem.resources.icon_arrow
import dev.k1een.qrt.core.designsystem.resources.icon_back
import dev.k1een.qrt.core.designsystem.resources.icon_board_games
import dev.k1een.qrt.core.designsystem.resources.icon_book
import dev.k1een.qrt.core.designsystem.resources.icon_car
import dev.k1een.qrt.core.designsystem.resources.icon_cartoon
import dev.k1een.qrt.core.designsystem.resources.icon_chart
import dev.k1een.qrt.core.designsystem.resources.icon_check
import dev.k1een.qrt.core.designsystem.resources.icon_check_circle
import dev.k1een.qrt.core.designsystem.resources.icon_chip
import dev.k1een.qrt.core.designsystem.resources.icon_comic
import dev.k1een.qrt.core.designsystem.resources.icon_computer
import dev.k1een.qrt.core.designsystem.resources.icon_film
import dev.k1een.qrt.core.designsystem.resources.icon_flask
import dev.k1een.qrt.core.designsystem.resources.icon_game
import dev.k1een.qrt.core.designsystem.resources.icon_globe
import dev.k1een.qrt.core.designsystem.resources.icon_home
import dev.k1een.qrt.core.designsystem.resources.icon_knowledge
import dev.k1een.qrt.core.designsystem.resources.icon_mathematics
import dev.k1een.qrt.core.designsystem.resources.icon_music
import dev.k1een.qrt.core.designsystem.resources.icon_palette
import dev.k1een.qrt.core.designsystem.resources.icon_paw
import dev.k1een.qrt.core.designsystem.resources.icon_retry
import dev.k1een.qrt.core.designsystem.resources.icon_scales
import dev.k1een.qrt.core.designsystem.resources.icon_shield
import dev.k1een.qrt.core.designsystem.resources.icon_skull
import dev.k1een.qrt.core.designsystem.resources.icon_sports
import dev.k1een.qrt.core.designsystem.resources.icon_star
import dev.k1een.qrt.core.designsystem.resources.icon_swords
import dev.k1een.qrt.core.designsystem.resources.icon_target
import dev.k1een.qrt.core.designsystem.resources.icon_television
import dev.k1een.qrt.core.designsystem.resources.icon_temple
import dev.k1een.qrt.core.designsystem.resources.icon_theatre
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
    Retry,
    Knowledge,
    Music,
    Theatre,
    Television,
    BoardGames,
    Computer,
    Mathematics,
    Temple,
    Sports,
    Scales,
    Palette,
    Star,
    Paw,
    Car,
    Comic,
    Chip,
    Anime,
    Cartoon,
    All,
    Film,
    Game,
    Shield,
    Skull,
    Check;

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
                Knowledge -> Res.drawable.icon_knowledge
                Music -> Res.drawable.icon_music
                Theatre -> Res.drawable.icon_theatre
                Television -> Res.drawable.icon_television
                BoardGames -> Res.drawable.icon_board_games
                Computer -> Res.drawable.icon_computer
                Mathematics -> Res.drawable.icon_mathematics
                Temple -> Res.drawable.icon_temple
                Sports -> Res.drawable.icon_sports
                Scales -> Res.drawable.icon_scales
                Palette -> Res.drawable.icon_palette
                Star -> Res.drawable.icon_star
                Paw -> Res.drawable.icon_paw
                Car -> Res.drawable.icon_car
                Comic -> Res.drawable.icon_comic
                Chip -> Res.drawable.icon_chip
                Anime -> Res.drawable.icon_anime
                Cartoon -> Res.drawable.icon_cartoon
                All -> Res.drawable.icon_all
                Film -> Res.drawable.icon_film
                Game -> Res.drawable.icon_game
                Shield -> Res.drawable.icon_shield
                Skull -> Res.drawable.icon_skull
                Check -> Res.drawable.icon_check
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
