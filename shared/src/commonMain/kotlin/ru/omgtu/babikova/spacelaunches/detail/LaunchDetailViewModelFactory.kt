package ru.omgtu.babikova.spacelaunches.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import kotlin.reflect.KClass
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

class LaunchDetailViewModelFactory(
    private val repository: LaunchRepository,
    private val navigator: Navigator,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        val launchId = checkNotNull(extras[LaunchIdKey]) { "launchId не передан в CreationExtras" }
        @Suppress("UNCHECKED_CAST")
        return LaunchDetailViewModel(launchId, repository, navigator) as T
    }

    companion object {
        val LaunchIdKey = CreationExtras.Key<String>()

        fun extrasFor(launchId: String): CreationExtras =
            MutableCreationExtras().apply { set(LaunchIdKey, launchId) }
    }
}
