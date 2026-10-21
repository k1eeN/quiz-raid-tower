package dev.k1een.qrt.feature.home.impl.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.feature.home.impl.presentation.home.mappers.HomeUiMapper
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeAction
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeSideEffect
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeState
import dev.k1een.qrt.feature.home.impl.presentation.home.models.HomeUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val uiMapper: HomeUiMapper,
) : ViewModel() {

    private val state = MutableStateFlow(HomeState())

    val uiState: StateFlow<HomeUiState> = state
        .map(uiMapper::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiMapper(state.value),
        )

    private val _sideEffect = MutableSharedFlow<HomeSideEffect>()
    val sideEffect: SharedFlow<HomeSideEffect> = _sideEffect.asSharedFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.StatisticsClicked -> postSideEffect(HomeSideEffect.OpenStatistics)
            HomeAction.StartRaidClicked -> postSideEffect(HomeSideEffect.StartRaid)
        }
    }

    private fun postSideEffect(effect: HomeSideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }
}
