package ru.omgtu.babikova.spacelaunches.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.omgtu.babikova.spacelaunches.Screen
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.ui.model.LaunchDetailUi
import ru.omgtu.babikova.spacelaunches.ui.model.toDetailUi
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

class LaunchDetailViewModel(
    private val launchId: String,
    private val repository: LaunchRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow<LaunchDetailUi?>(null)
    val state: StateFlow<LaunchDetailUi?> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = repository.getLaunch(launchId).toDetailUi()
        }
    }

    fun onIntent(intent: LaunchDetailIntent) {
        when (intent) {
            is LaunchDetailIntent.ProviderClicked -> navigator.addToBackStack(Screen.Agency(intent.id))
            is LaunchDetailIntent.PadClicked -> navigator.addToBackStack(Screen.Pad(intent.id))
        }
    }
}
