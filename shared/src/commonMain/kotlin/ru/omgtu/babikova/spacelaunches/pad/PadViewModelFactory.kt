package ru.omgtu.babikova.spacelaunches.pad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import kotlin.reflect.KClass
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository

class PadViewModelFactory(
    private val repository: LaunchRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        val padId = checkNotNull(extras[PadIdKey]) { "padId не передан в CreationExtras" }
        @Suppress("UNCHECKED_CAST")
        return PadViewModel(padId, repository) as T
    }

    companion object {
        val PadIdKey = CreationExtras.Key<Int>()

        fun extrasFor(padId: Int): CreationExtras =
            MutableCreationExtras().apply { set(PadIdKey, padId) }
    }
}
