package dev.k1een.qrt.feature.home.impl.presentation.statistics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.feature.home.impl.presentation.statistics.mappers.StatisticsUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsAction
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsSideEffect
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsState
import dev.k1een.qrt.feature.home.impl.presentation.statistics.models.StatisticsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class StatisticsViewModel(
    private val uiMapper: StatisticsUiMapper,
) : ViewModel() {

    private val state = MutableStateFlow(StatisticsState())

    val uiState: StateFlow<StatisticsUiState> = state
        .map(uiMapper::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiMapper(state.value),
        )

    private val _sideEffect = MutableSharedFlow<StatisticsSideEffect>()
    val sideEffect: SharedFlow<StatisticsSideEffect> = _sideEffect.asSharedFlow()

    fun onAction(action: StatisticsAction) {
        when (action) {
            StatisticsAction.BackClicked -> postSideEffect(StatisticsSideEffect.GoHome)
            StatisticsAction.StartRaidClicked -> postSideEffect(StatisticsSideEffect.StartRaid)
        }
    }

    private fun postSideEffect(effect: StatisticsSideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }
}
