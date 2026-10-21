package dev.k1een.qrt.core.designsystem.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.k1een.qrt.core.designsystem.R

fun preloadTowerAnimation(context: Context) {
    LottieCompositionFactory.fromRawRes(context, R.raw.qrt_loading)
}

@Composable
internal actual fun TowerAnimation(modifier: Modifier) {
    val inspection = LocalInspectionMode.current
    val composition =
        if (inspection) {
            val context = LocalContext.current
            remember(context) {
                LottieCompositionFactory.fromRawResSync(context, R.raw.qrt_loading).value
            }
        } else {
            val loaded by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.qrt_loading))
            loaded
        }
    val progress by
        animateLottieCompositionAsState(
            composition,
            isPlaying = !inspection,
            iterations = LottieConstants.IterateForever,
        )
    LottieAnimation(
        composition,
        progress = { if (inspection) .25f else progress },
        modifier = modifier,
    )
}
