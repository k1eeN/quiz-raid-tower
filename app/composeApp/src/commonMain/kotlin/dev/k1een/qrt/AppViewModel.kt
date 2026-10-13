package dev.k1een.qrt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.k1een.qrt.core.database.domain.repository.SessionTokenRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class AppViewModel(
    private val sessionTokenRepository: SessionTokenRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            runCatching {
                sessionTokenRepository.requestAndStoreToken()
            }.onFailure { exception ->
                if (exception is CancellationException)
                    throw exception
            }
        }
    }
}
