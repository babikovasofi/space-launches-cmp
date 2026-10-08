package ru.omgtu.babikova.spacelaunches.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.search_hint
import ru.omgtu.babikova.spacelaunches.ui.components.LaunchCard
import ru.omgtu.babikova.spacelaunches.ui.list.LaunchListIntent
import ru.omgtu.babikova.spacelaunches.ui.list.LaunchListState

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
        LazyColumn(
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
}
