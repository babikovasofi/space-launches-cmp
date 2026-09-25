package ru.omgtu.babikova.spacelaunches.pad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.domain.Pad

class PadViewModel(
    padId: Int,
    repository: LaunchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<Pad?>(null)
    val state: StateFlow<Pad?> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = repository.getPad(padId)
        }
    }
}
