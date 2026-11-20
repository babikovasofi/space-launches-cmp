package ru.omgtu.babikova.spacelaunches.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.first
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.search_hint
import ru.omgtu.babikova.spacelaunches.ui.components.LaunchCard
import ru.omgtu.babikova.spacelaunches.ui.components.LoadContent
import ru.omgtu.babikova.spacelaunches.ui.components.LoadError
import ru.omgtu.babikova.spacelaunches.ui.components.LoadingIndicator
import ru.omgtu.babikova.spacelaunches.ui.list.LaunchListIntent
import ru.omgtu.babikova.spacelaunches.ui.list.LaunchListState
import ru.omgtu.babikova.spacelaunches.ui.list.NextPage

@Composable
fun LaunchListScreen(
    state: LaunchListState,
    onIntent: (LaunchListIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = state.query,
            onValueChange = { onIntent(LaunchListIntent.QueryChanged(it)) },
            label = { Text(stringResource(Res.string.search_hint)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )
        LoadContent(state.content, onRetry = { onIntent(LaunchListIntent.Retry) }) { content ->
            key(state.query) {
                val listState = rememberLazyListState()
                val loadNextPage = { onIntent(LaunchListIntent.LoadNextPage) }

                if (content.nextOffset != null && content.nextPage == NextPage.Idle) {
                    LaunchedEffect(listState, content.nextOffset) {
                        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                            .first { it != null && it >= content.items.lastIndex }
                        loadNextPage()
                    }
                }

                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(content.items, key = { it.id }) { card ->
                        LaunchCard(
                            card = card,
                            onClick = { onIntent(LaunchListIntent.CardClicked(card.id)) },
                        )
                    }
                    when (content.nextPage) {
                        NextPage.Loading -> item { LoadingIndicator() }
                        NextPage.Error -> item { LoadError(loadNextPage) }
                        NextPage.Idle -> Unit
                    }
                }
            }
        }
    }
}
