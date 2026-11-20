package ru.omgtu.babikova.spacelaunches.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.omgtu.babikova.spacelaunches.Screen
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.ui.LoadState
import ru.omgtu.babikova.spacelaunches.ui.loadState
import ru.omgtu.babikova.spacelaunches.ui.model.toCardUi
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

private const val SEARCH_DELAY_MS = 350L

class LaunchListViewModel(
    private val repository: LaunchRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchListState())
    val state = _state.asStateFlow()

    private var request: Job? = null

    init {
        loadFirstPage("")
    }

    fun onIntent(intent: LaunchListIntent) {
        when (intent) {
            is LaunchListIntent.QueryChanged -> loadFirstPage(intent.value, waitForTyping = true)
            is LaunchListIntent.CardClicked -> navigator.addToBackStack(Screen.Detail(intent.id))
            LaunchListIntent.Retry -> loadFirstPage(_state.value.query)
            LaunchListIntent.LoadNextPage -> loadNextPage()
        }
    }

    private fun loadFirstPage(query: String, waitForTyping: Boolean = false) {
        request?.cancel()
        _state.value = LaunchListState(query)
        request = viewModelScope.launch {
            if (waitForTyping) delay(SEARCH_DELAY_MS)
            val content = loadState {
                val page = repository.getLaunches(query, offset = 0)
                if (page.items.isEmpty()) null else withContext(Dispatchers.Default) {
                    LaunchListContent(page.items.map { it.toCardUi() }.distinctBy { it.id }, page.nextOffset)
                }
            }
            _state.value = LaunchListState(query, content)
        }
    }

    private fun loadNextPage() {
        val current = _state.value
        val content = (current.content as? LoadState.Loaded)?.value ?: return
        val offset = content.nextOffset ?: return
        if (content.nextPage == NextPage.Loading) return

        _state.value = current.copy(content = LoadState.Loaded(content.copy(nextPage = NextPage.Loading)))
        request = viewModelScope.launch {
            val result = loadState {
                val page = repository.getLaunches(current.query, offset)
                withContext(Dispatchers.Default) {
                    val items = (content.items + page.items.map { it.toCardUi() }).distinctBy { it.id }
                    LaunchListContent(items, page.nextOffset)
                }
            }
            _state.value = current.copy(
                content = when (result) {
                    is LoadState.Loaded -> result
                    else -> LoadState.Loaded(content.copy(nextPage = NextPage.Error))
                },
            )
        }
    }
}
