package ru.omgtu.babikova.spacelaunches.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.omgtu.babikova.spacelaunches.Screen

class Navigator {

    private val _navStack = MutableStateFlow<List<Screen>>(listOf(Screen.List))
    val navStack: StateFlow<List<Screen>> = _navStack.asStateFlow()

    fun addToBackStack(screen: Screen) {
        _navStack.update { it + screen }
    }

    fun back() {
        _navStack.update { stack -> if (stack.size > 1) stack.dropLast(1) else stack }
    }
}
