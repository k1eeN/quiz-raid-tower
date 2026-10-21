package dev.k1een.qrt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.core.database.domain.repository.SessionTokenRepository
import dev.k1een.qrt.mappers.AppUiMapper
import dev.k1een.qrt.models.AppAction
import dev.k1een.qrt.models.AppSideEffect
import dev.k1een.qrt.models.AppState
import dev.k1een.qrt.models.AppUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AppViewModel(
    private val sessionTokenRepository: SessionTokenRepository,
    private val uiMapper: AppUiMapper,
) : ViewModel() {

    private val state = MutableStateFlow(AppState())

    val uiState: StateFlow<AppUiState> = state
        .map(uiMapper::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiMapper(state.value),
        )

    private val _sideEffect = MutableSharedFlow<AppSideEffect>()
    val sideEffect: SharedFlow<AppSideEffect> = _sideEffect.asSharedFlow()

    init {
        onAction(AppAction.Load)
    }

    fun onAction(action: AppAction) {
        when (action) {
            AppAction.Load -> loadSessionToken()
            AppAction.HomeClicked -> {
                if (state.value.state == AppState.State.Error) {
                    updateState { state ->
                        state.copy(
                            state = AppState.State.Content,
                        )
                    }
                    postSideEffect(AppSideEffect.NavigateToHome)
                }
            }
        }
    }

    private fun loadSessionToken() {
        updateState { state ->
            state.copy(
                state = AppState.State.Loading,
            )
        }
        viewModelScope.launch {
            runCatching { sessionTokenRepository.requestAndStoreToken() }
                .onSuccess {
                    updateState { state ->
                        state.copy(
                            state = AppState.State.Content,
                        )
                    }
                }
                .onFailure { exception ->
                    if (exception is CancellationException) throw exception
                    updateState { state ->
                        state.copy(
                            state = AppState.State.Error,
                        )
                    }
                }
        }
    }

    private fun postSideEffect(effect: AppSideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }

    private fun updateState(block: (AppState) -> AppState) {
        state.update(block)
    }
}
