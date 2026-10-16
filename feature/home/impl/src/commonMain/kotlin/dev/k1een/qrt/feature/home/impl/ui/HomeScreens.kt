package dev.k1een.qrt.feature.home.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
internal fun HomeScreen(
    onStartRaid: () -> Unit,
    onStatistics: () -> Unit,
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Home · Главная", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onStartRaid) { Text("Начать рейд") }
        Button(onClick = onStatistics) { Text("Статистика") }
    }
}

@Composable
internal fun StatisticsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Statistics · Статистика", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onBack) { Text("Назад") }
    }
}
