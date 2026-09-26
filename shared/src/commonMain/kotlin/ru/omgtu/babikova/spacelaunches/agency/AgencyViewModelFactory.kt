package ru.omgtu.babikova.spacelaunches.agency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import kotlin.reflect.KClass
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository

class AgencyViewModelFactory(
    private val repository: LaunchRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        val agencyId = checkNotNull(extras[AgencyIdKey]) { "agencyId не передан в CreationExtras" }
        @Suppress("UNCHECKED_CAST")
        return AgencyViewModel(agencyId, repository) as T
    }

    companion object {
        val AgencyIdKey = CreationExtras.Key<Int>()

        fun extrasFor(agencyId: Int): CreationExtras =
            MutableCreationExtras().apply { set(AgencyIdKey, agencyId) }
    }
}
