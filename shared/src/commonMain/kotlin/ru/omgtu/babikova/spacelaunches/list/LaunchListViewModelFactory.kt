package ru.omgtu.babikova.spacelaunches.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

class LaunchListViewModelFactory(
    private val repository: LaunchRepository,
    private val navigator: Navigator,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        @Suppress("UNCHECKED_CAST")
        return LaunchListViewModel(repository, navigator) as T
    }
}
