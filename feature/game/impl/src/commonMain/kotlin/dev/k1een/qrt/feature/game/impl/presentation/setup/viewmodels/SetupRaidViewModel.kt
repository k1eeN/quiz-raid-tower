package dev.k1een.qrt.feature.game.impl.presentation.setup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.feature.game.api.usecase.GetCategoriesUseCase
import dev.k1een.qrt.feature.game.impl.presentation.setup.mappers.SetupRaidUiMapper
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidAction
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidSideEffect
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidState
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidUiState
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

internal class SetupRaidViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val uiMapper: SetupRaidUiMapper,
) : ViewModel() {

    private val state = MutableStateFlow(SetupRaidState())

    val uiState: StateFlow<SetupRaidUiState> = state
        .map(uiMapper::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiMapper(state.value),
        )

    private val _sideEffect = MutableSharedFlow<SetupRaidSideEffect>()
    val sideEffect: SharedFlow<SetupRaidSideEffect> = _sideEffect.asSharedFlow()

    init {
        onAction(SetupRaidAction.Load)
    }

    fun onAction(action: SetupRaidAction) {
        when (action) {
            SetupRaidAction.Load -> loadCategories()
            SetupRaidAction.RetryClicked -> {
                if (state.value.state == SetupRaidState.State.Error) {
                    loadCategories()
                }
            }

            is SetupRaidAction.CategoryClicked -> updateState { state ->
                state.copy(
                    selectedCategoryId = action.id,
                )
            }

            is SetupRaidAction.DifficultyClicked -> updateState { state ->
                state.copy(
                    difficulty = action.difficulty,
                )
            }

            SetupRaidAction.StartRaidClicked -> postSideEffect(
                SetupRaidSideEffect.StartRaid(
                    categoryId = state.value.selectedCategoryId,
                    difficulty = state.value.difficulty.apiValue,
                ),
            )
            SetupRaidAction.BackClicked -> postSideEffect(SetupRaidSideEffect.GoBack)
            SetupRaidAction.HomeClicked -> postSideEffect(SetupRaidSideEffect.GoHome)
        }
    }

    private fun loadCategories() {
        updateState { state ->
            state.copy(
                state = SetupRaidState.State.Loading,
            )
        }
        viewModelScope.launch {
            runCatching { getCategoriesUseCase() }
                .onSuccess { categories ->
                    updateState { state ->
                        state.copy(
                            state = SetupRaidState.State.Content(categories = categories),
                        )
                    }
                }
                .onFailure { exception ->
                    if (exception is CancellationException) throw exception
                    updateState { state ->
                        state.copy(
                            state = SetupRaidState.State.Error,
                        )
                    }
                }
        }
    }

    private fun postSideEffect(effect: SetupRaidSideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }

    private fun updateState(block: (SetupRaidState) -> SetupRaidState) {
        state.update(block)
    }
}
