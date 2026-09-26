package ru.omgtu.babikova.spacelaunches.ui.screens.pad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.domain.Pad
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.pad_coordinates
import ru.omgtu.babikova.spacelaunches.resources.pad_country
import ru.omgtu.babikova.spacelaunches.resources.pad_launches_total
import ru.omgtu.babikova.spacelaunches.resources.pad_location
import ru.omgtu.babikova.spacelaunches.ui.components.CardSurface
import ru.omgtu.babikova.spacelaunches.ui.components.FactRow

@Composable
fun PadScreen(
    pad: Pad,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = pad.name,
            style = MaterialTheme.typography.headlineSmall,
        )
        CardSurface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FactRow(stringResource(Res.string.pad_location), pad.location.name)
                FactRow(stringResource(Res.string.pad_country), pad.countryCode)
                FactRow(
                    stringResource(Res.string.pad_coordinates),
                    "${pad.latitude}, ${pad.longitude}",
                )
                FactRow(
                    stringResource(Res.string.pad_launches_total),
                    pad.totalLaunchCount.toString(),
                )
            }
        }
    }
}
