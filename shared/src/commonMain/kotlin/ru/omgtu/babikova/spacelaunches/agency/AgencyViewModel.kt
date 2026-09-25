package ru.omgtu.babikova.spacelaunches.agency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.omgtu.babikova.spacelaunches.domain.Agency
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository

class AgencyViewModel(
    agencyId: Int,
    repository: LaunchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<Agency?>(null)
    val state: StateFlow<Agency?> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = repository.getAgency(agencyId)
        }
    }
}
