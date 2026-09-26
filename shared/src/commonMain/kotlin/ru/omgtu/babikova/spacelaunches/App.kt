package ru.omgtu.babikova.spacelaunches

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.agency.AgencyViewModelFactory
import ru.omgtu.babikova.spacelaunches.data.LaunchRepositoryImpl
import ru.omgtu.babikova.spacelaunches.detail.LaunchDetailViewModelFactory
import ru.omgtu.babikova.spacelaunches.domain.LaunchRepository
import ru.omgtu.babikova.spacelaunches.list.LaunchListViewModelFactory
import ru.omgtu.babikova.spacelaunches.pad.PadViewModelFactory
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.action_toggle_theme
import ru.omgtu.babikova.spacelaunches.resources.ic_theme
import ru.omgtu.babikova.spacelaunches.ui.AppTheme
import ru.omgtu.babikova.spacelaunches.ui.components.AppScaffold
import ru.omgtu.babikova.spacelaunches.ui.navigation.AppNavDisplay
import ru.omgtu.babikova.spacelaunches.ui.navigation.Navigator

@Composable
fun App() {
    var darkTheme by remember { mutableStateOf(false) }

    AppTheme(darkTheme = darkTheme) {
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
            actions = {
                IconButton(onClick = { darkTheme = !darkTheme }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_theme),
                        contentDescription = stringResource(Res.string.action_toggle_theme),
                    )
                }
            },
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
