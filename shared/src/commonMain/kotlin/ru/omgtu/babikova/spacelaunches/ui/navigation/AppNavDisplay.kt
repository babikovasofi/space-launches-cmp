package ru.omgtu.babikova.spacelaunches.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ru.omgtu.babikova.spacelaunches.Screen
import ru.omgtu.babikova.spacelaunches.agency.AgencyViewModel
import ru.omgtu.babikova.spacelaunches.agency.AgencyViewModelFactory
import ru.omgtu.babikova.spacelaunches.detail.LaunchDetailViewModel
import ru.omgtu.babikova.spacelaunches.detail.LaunchDetailViewModelFactory
import ru.omgtu.babikova.spacelaunches.list.LaunchListViewModel
import ru.omgtu.babikova.spacelaunches.list.LaunchListViewModelFactory
import ru.omgtu.babikova.spacelaunches.pad.PadViewModel
import ru.omgtu.babikova.spacelaunches.pad.PadViewModelFactory
import ru.omgtu.babikova.spacelaunches.ui.screens.agency.AgencyScreen
import ru.omgtu.babikova.spacelaunches.ui.screens.detail.LaunchDetailScreen
import ru.omgtu.babikova.spacelaunches.ui.screens.list.LaunchListScreen
import ru.omgtu.babikova.spacelaunches.ui.screens.pad.PadScreen

private const val TRANSITION_MS = 300

@Composable
fun AppNavDisplay(
    navigator: Navigator,
    listViewModelFactory: LaunchListViewModelFactory,
    detailViewModelFactory: LaunchDetailViewModelFactory,
    agencyViewModelFactory: AgencyViewModelFactory,
    padViewModelFactory: PadViewModelFactory,
    modifier: Modifier = Modifier,
) {
    val backStack by navigator.navStack.collectAsStateWithLifecycle()
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { navigator.back() },
        transitionSpec = { slide(SlideDirection.Start) },
        popTransitionSpec = { slide(SlideDirection.End) },
        predictivePopTransitionSpec = { slide(SlideDirection.End) },
        entryProvider = entryProvider {
            entry<Screen.List> {
                val viewModel: LaunchListViewModel = viewModel(factory = listViewModelFactory)
                val state by viewModel.state.collectAsStateWithLifecycle()
                LaunchListScreen(state = state, onIntent = viewModel::onIntent)
            }
            entry<Screen.Detail> { key ->
                val viewModel: LaunchDetailViewModel = viewModel(
                    key = "detail-${key.id}",
                    factory = detailViewModelFactory,
                    extras = LaunchDetailViewModelFactory.extrasFor(key.id),
                )
                val state by viewModel.state.collectAsStateWithLifecycle()
                state?.let { LaunchDetailScreen(launch = it, onIntent = viewModel::onIntent) }
            }
            entry<Screen.Agency> { key ->
                val viewModel: AgencyViewModel = viewModel(
                    key = "agency-${key.id}",
                    factory = agencyViewModelFactory,
                    extras = AgencyViewModelFactory.extrasFor(key.id),
                )
                val state by viewModel.state.collectAsStateWithLifecycle()
                state?.let { AgencyScreen(agency = it) }
            }
            entry<Screen.Pad> { key ->
                val viewModel: PadViewModel = viewModel(
                    key = "pad-${key.id}",
                    factory = padViewModelFactory,
                    extras = PadViewModelFactory.extrasFor(key.id),
                )
                val state by viewModel.state.collectAsStateWithLifecycle()
                state?.let { PadScreen(pad = it) }
            }
        },
    )
}

private fun AnimatedContentTransitionScope<*>.slide(direction: SlideDirection): ContentTransform =
    (slideIntoContainer(direction, tween(TRANSITION_MS)) + fadeIn(tween(TRANSITION_MS)))
        .togetherWith(
            slideOutOfContainer(direction, tween(TRANSITION_MS)) + fadeOut(tween(TRANSITION_MS)),
        )
