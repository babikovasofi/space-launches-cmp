package ru.omgtu.babikova.spacelaunches.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF1B4E86),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD7E3FF),
    onPrimaryContainer = Color(0xFF002F62),
    secondary = Color(0xFF545F70),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFDFBFF),
    onBackground = Color(0xFF1A1C1E),
    surface = Color(0xFFFDFBFF),
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFDFE2EB),
    onSurfaceVariant = Color(0xFF43474E),
    surfaceContainer = Color(0xFFEEF0F7),
    outline = Color(0xFF73777F),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA9C7FF),
    onPrimary = Color(0xFF00315C),
    primaryContainer = Color(0xFF004881),
    onPrimaryContainer = Color(0xFFD7E3FF),
    secondary = Color(0xFFBBC7DB),
    onSecondary = Color(0xFF253140),
    background = Color(0xFF121316),
    onBackground = Color(0xFFE3E2E6),
    surface = Color(0xFF121316),
    onSurface = Color(0xFFE3E2E6),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C6CF),
    surfaceContainer = Color(0xFF1E2023),
    outline = Color(0xFF8D9199),
)

private val StatusSuccess = Color(0xFF1B7F4B)
private val StatusFailure = Color(0xFFB3261E)
private val StatusGo = Color(0xFF1B5E9E)
private val StatusPlanned = Color(0xFF7A6024)

@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}

fun launchStatusColor(statusId: Int): Color = when (statusId) {
    1 -> StatusGo
    3 -> StatusSuccess
    4 -> StatusFailure
    else -> StatusPlanned
}
