package ru.omgtu.babikova.spacelaunches.ui.pad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.omgtu.babikova.spacelaunches.ui.LoadState
import ru.omgtu.babikova.spacelaunches.ui.loadState
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.domain.Pad

class PadViewModel(
    private val padId: Int,
    private val repository: LaunchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<LoadState<Pad>>(LoadState.Loading)
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.value = LoadState.Loading
        viewModelScope.launch {
            _state.value = loadState { repository.getPad(padId) }
        }
    }
}
