package dev.k1een.qrt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.core.database.domain.repository.SessionTokenRepository
import dev.k1een.qrt.feature.game.api.usecase.GetCategoriesUseCase
import dev.k1een.qrt.feature.game.api.usecase.GetQuestionsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class AppViewModel(
    private val sessionTokenRepository: SessionTokenRepository,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase,
) : ViewModel() {

    var text by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            requestSessionToken()
            delay(5_000.milliseconds)
            loadCategories()
            delay(5_000.milliseconds)
            loadQuestions()
        }
    }

    private suspend fun requestSessionToken() {
        runCatching {
            sessionTokenRepository.requestAndStoreToken()
        }.onFailure { exception ->
            if (exception is CancellationException)
                throw exception
        }
    }

    private suspend fun loadCategories() {
        runCatching {
            getCategoriesUseCase()
        }.onSuccess { categories ->
            text = categories.toString()
        }.onFailure { exception ->
            if (exception is CancellationException)
                throw exception
        }
    }

    private suspend fun loadQuestions() {
        runCatching {
            getQuestionsUseCase(amount = 5)
        }.onSuccess { questions ->
            text += "\n$questions"
        }.onFailure { exception ->
            if (exception is CancellationException)
                throw exception
        }
    }
}
