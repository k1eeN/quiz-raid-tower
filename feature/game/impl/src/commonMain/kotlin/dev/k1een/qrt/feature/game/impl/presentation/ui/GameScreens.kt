package dev.k1een.qrt.feature.game.impl.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.k1een.qrt.feature.game.impl.resources.Res
import dev.k1een.qrt.feature.game.impl.resources.battle
import dev.k1een.qrt.feature.game.impl.resources.floor_completed
import dev.k1een.qrt.feature.game.impl.resources.home
import dev.k1een.qrt.feature.game.impl.resources.new_raid
import dev.k1een.qrt.feature.game.impl.resources.next
import dev.k1een.qrt.feature.game.impl.resources.next_battle
import dev.k1een.qrt.feature.game.impl.resources.raid_result
import dev.k1een.qrt.feature.game.impl.resources.result
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun BattleScreen(onNext: () -> Unit, onResult: () -> Unit) {
    MockGameScreen(stringResource(Res.string.battle)) {
        Button(onClick = onNext) { Text(stringResource(Res.string.next)) }
        Button(onClick = onResult) { Text(stringResource(Res.string.result)) }
    }
}

@Composable
internal fun FloorCompletedScreen(onNextBattle: () -> Unit, onResult: () -> Unit) {
    MockGameScreen(stringResource(Res.string.floor_completed)) {
        Button(onClick = onNextBattle) { Text(stringResource(Res.string.next_battle)) }
        Button(onClick = onResult) { Text(stringResource(Res.string.result)) }
    }
}

@Composable
internal fun RaidResultScreen(onHome: () -> Unit, onNewRaid: () -> Unit) {
    MockGameScreen(stringResource(Res.string.raid_result)) {
        Button(onClick = onHome) { Text(stringResource(Res.string.home)) }
        Button(onClick = onNewRaid) { Text(stringResource(Res.string.new_raid)) }
    }
}

@Composable
private fun MockGameScreen(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.safeDrawingPadding().verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        content()
    }
}
