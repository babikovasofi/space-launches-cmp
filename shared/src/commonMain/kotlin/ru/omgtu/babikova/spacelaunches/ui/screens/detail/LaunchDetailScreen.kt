package ru.omgtu.babikova.spacelaunches.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.omgtu.babikova.spacelaunches.detail.LaunchDetailIntent
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.detail_mission
import ru.omgtu.babikova.spacelaunches.resources.detail_mission_type
import ru.omgtu.babikova.spacelaunches.resources.detail_net
import ru.omgtu.babikova.spacelaunches.resources.detail_orbit
import ru.omgtu.babikova.spacelaunches.resources.detail_pad
import ru.omgtu.babikova.spacelaunches.resources.detail_provider
import ru.omgtu.babikova.spacelaunches.resources.detail_rocket
import ru.omgtu.babikova.spacelaunches.ui.components.CardSurface
import ru.omgtu.babikova.spacelaunches.ui.components.FactRow
import ru.omgtu.babikova.spacelaunches.ui.components.LinkRow
import ru.omgtu.babikova.spacelaunches.ui.components.StatusChip
import ru.omgtu.babikova.spacelaunches.ui.model.LaunchDetailUi
import ru.omgtu.babikova.spacelaunches.ui.model.rocketImage

@Composable
fun LaunchDetailScreen(
    launch: LaunchDetailUi,
    onIntent: (LaunchDetailIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(rocketImage(launch.rocketConfigurationId)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
        )
        Text(
            text = launch.name,
            style = MaterialTheme.typography.headlineSmall,
        )
        StatusChip(statusName = launch.statusName)
        Text(
            text = launch.statusDescription,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        CardSurface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FactRow(stringResource(Res.string.detail_net), launch.launchTime)
                FactRow(stringResource(Res.string.detail_rocket), launch.rocketFullName)
            }
        }
        if (launch.missionName != null) {
            CardSurface(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.detail_mission),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = launch.missionName,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    launch.missionType?.let {
                        FactRow(stringResource(Res.string.detail_mission_type), it)
                    }
                    launch.missionOrbit?.let {
                        FactRow(stringResource(Res.string.detail_orbit), it)
                    }
                    launch.missionDescription?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
        LinkRow(
            label = stringResource(Res.string.detail_provider),
            value = launch.providerName,
            onClick = { onIntent(LaunchDetailIntent.ProviderClicked(launch.providerId)) },
        )
        LinkRow(
            label = stringResource(Res.string.detail_pad),
            value = launch.padName,
            onClick = { onIntent(LaunchDetailIntent.PadClicked(launch.padId)) },
        )
    }
}
