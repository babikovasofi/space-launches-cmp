package ru.omgtu.babikova.spacelaunches.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.omgtu.babikova.spacelaunches.Screen
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.ui.model.toCardUi
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

class LaunchListViewModel(
    private val repository: LaunchRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchListState())
    val state: StateFlow<LaunchListState> = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        search(_state.value.query)
    }

    fun onIntent(intent: LaunchListIntent) {
        when (intent) {
            is LaunchListIntent.QueryChanged -> {
                _state.update { it.copy(query = intent.value) }
                search(intent.value)
            }

            is LaunchListIntent.CardClicked -> navigator.addToBackStack(Screen.Detail(intent.id))
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val items = repository.getLaunches(query).map { it.toCardUi() }
            _state.update { it.copy(items = items) }
        }
    }
}
