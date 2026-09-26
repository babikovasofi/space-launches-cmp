package ru.omgtu.babikova.spacelaunches.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.omgtu.babikova.spacelaunches.list.LaunchListIntent
import ru.omgtu.babikova.spacelaunches.list.LaunchListState
import ru.omgtu.babikova.spacelaunches.ui.components.LaunchCard

@Composable
fun LaunchListScreen(
    state: LaunchListState,
    onIntent: (LaunchListIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.items, key = { it.id }) { card ->
            LaunchCard(
                card = card,
                onClick = { onIntent(LaunchListIntent.CardClicked(card.id)) },
            )
        }
    }
}
