package ru.omgtu.babikova.spacelaunches.ui.screens.agency

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
import ru.omgtu.babikova.spacelaunches.domain.Agency
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.agency_country
import ru.omgtu.babikova.spacelaunches.resources.agency_founded
import ru.omgtu.babikova.spacelaunches.resources.agency_launches_total
import ru.omgtu.babikova.spacelaunches.resources.agency_type
import ru.omgtu.babikova.spacelaunches.ui.components.CardSurface
import ru.omgtu.babikova.spacelaunches.ui.components.FactRow

@Composable
fun AgencyScreen(
    agency: Agency,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = agency.name,
            style = MaterialTheme.typography.headlineSmall,
        )
        CardSurface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FactRow(stringResource(Res.string.agency_type), agency.type)
                FactRow(stringResource(Res.string.agency_country), agency.countryCode)
                agency.foundingYear?.let {
                    FactRow(stringResource(Res.string.agency_founded), it.toString())
                }
                FactRow(
                    stringResource(Res.string.agency_launches_total),
                    agency.totalLaunchCount.toString(),
                )
            }
        }
    }
}
