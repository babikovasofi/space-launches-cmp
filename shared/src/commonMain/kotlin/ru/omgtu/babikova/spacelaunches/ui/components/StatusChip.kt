package ru.omgtu.babikova.spacelaunches.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.omgtu.babikova.spacelaunches.ui.launchStatusColor

@Composable
fun StatusChip(
    statusId: Int,
    statusName: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = statusName,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(launchStatusColor(statusId))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}
