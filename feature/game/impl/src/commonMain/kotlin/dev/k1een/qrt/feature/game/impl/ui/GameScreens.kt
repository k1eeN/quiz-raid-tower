package dev.k1een.qrt.feature.game.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SetupRaidScreen(onNext: () -> Unit) {
    MockGameScreen("SetupRaid · Подготовка") {
        Button(onClick = onNext) { Text("Далее") }
    }
}

@Composable
internal fun BattleScreen(onNext: () -> Unit, onResult: () -> Unit) {
    MockGameScreen("Battle · Бой") {
        Button(onClick = onNext) { Text("Далее") }
        Button(onClick = onResult) { Text("Результат") }
    }
}

@Composable
internal fun FloorCompletedScreen(onNextBattle: () -> Unit, onResult: () -> Unit) {
    MockGameScreen("FloorCompleted · Этаж пройден") {
        Button(onClick = onNextBattle) { Text("Следующий бой") }
        Button(onClick = onResult) { Text("Результат") }
    }
}

@Composable
internal fun RaidResultScreen(onHome: () -> Unit, onNewRaid: () -> Unit) {
    MockGameScreen("RaidResult · Результат") {
        Button(onClick = onHome) { Text("На главную") }
        Button(onClick = onNewRaid) { Text("Новый рейд") }
    }
}

@Composable
private fun MockGameScreen(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        content()
    }
}
