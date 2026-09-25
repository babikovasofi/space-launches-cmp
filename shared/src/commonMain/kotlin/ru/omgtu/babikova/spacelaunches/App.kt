package ru.omgtu.babikova.spacelaunches

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.omgtu.babikova.spacelaunches.agency.AgencyViewModelFactory
import ru.omgtu.babikova.spacelaunches.data.LaunchRepositoryImpl
import ru.omgtu.babikova.spacelaunches.detail.LaunchDetailViewModelFactory
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.list.LaunchListViewModelFactory
import ru.omgtu.babikova.spacelaunches.pad.PadViewModelFactory
import ru.omgtu.babikova.spacelaunches.ui.AppTheme
import ru.omgtu.babikova.spacelaunches.ui.components.AppScaffold
import ru.omgtu.babikova.spacelaunches.ui.navigation.AppNavDisplay
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

@Composable
fun App() {
    AppTheme(darkTheme = false) {
        val repository: LaunchRepository = remember { LaunchRepositoryImpl() }
        val navigator = remember { Navigator() }
        val listViewModelFactory = remember { LaunchListViewModelFactory(repository, navigator) }
        val detailViewModelFactory = remember { LaunchDetailViewModelFactory(repository, navigator) }
        val agencyViewModelFactory = remember { AgencyViewModelFactory(repository) }
        val padViewModelFactory = remember { PadViewModelFactory(repository) }

        val navStack by navigator.navStack.collectAsStateWithLifecycle()
        val canGoBack by remember { derivedStateOf { navStack.size > 1 } }

        AppScaffold(
            onBack = navigator::back.takeIf { canGoBack },
        ) { modifier ->
            AppNavDisplay(
                navigator = navigator,
                listViewModelFactory = listViewModelFactory,
                detailViewModelFactory = detailViewModelFactory,
                agencyViewModelFactory = agencyViewModelFactory,
                padViewModelFactory = padViewModelFactory,
                modifier = modifier,
            )
        }
    }
}
