package ru.omgtu.babikova.spacelaunches.ui.model

import org.jetbrains.compose.resources.DrawableResource
import ru.omgtu.babikova.spacelaunches.resources.Res
import ru.omgtu.babikova.spacelaunches.resources.ariane_62
import ru.omgtu.babikova.spacelaunches.resources.atlas_v_551
import ru.omgtu.babikova.spacelaunches.resources.ceres_1
import ru.omgtu.babikova.spacelaunches.resources.electron
import ru.omgtu.babikova.spacelaunches.resources.falcon_9
import ru.omgtu.babikova.spacelaunches.resources.gravity_1
import ru.omgtu.babikova.spacelaunches.resources.h3_24
import ru.omgtu.babikova.spacelaunches.resources.kinetica_1
import ru.omgtu.babikova.spacelaunches.resources.long_march_2f_g
import ru.omgtu.babikova.spacelaunches.resources.long_march_4c
import ru.omgtu.babikova.spacelaunches.resources.long_march_8a
import ru.omgtu.babikova.spacelaunches.resources.lvm_3
import ru.omgtu.babikova.spacelaunches.resources.new_glenn
import ru.omgtu.babikova.spacelaunches.resources.new_shepard
import ru.omgtu.babikova.spacelaunches.resources.nuri
import ru.omgtu.babikova.spacelaunches.resources.soyuz_2_1b
import ru.omgtu.babikova.spacelaunches.resources.starship_v2
import ru.omgtu.babikova.spacelaunches.resources.starship_v3
import ru.omgtu.babikova.spacelaunches.resources.vulcan_vc6l

private val rocketImages = mapOf(
    15 to Res.drawable.soyuz_2_1b,
    26 to Res.drawable.electron,
    27 to Res.drawable.atlas_v_551,
    64 to Res.drawable.long_march_4c,
    84 to Res.drawable.long_march_2f_g,
    117 to Res.drawable.nuri,
    121 to Res.drawable.ariane_62,
    137 to Res.drawable.new_shepard,
    138 to Res.drawable.new_glenn,
    164 to Res.drawable.falcon_9,
    172 to Res.drawable.lvm_3,
    204 to Res.drawable.h3_24,
    461 to Res.drawable.ceres_1,
    479 to Res.drawable.vulcan_vc6l,
    483 to Res.drawable.kinetica_1,
    503 to Res.drawable.gravity_1,
    518 to Res.drawable.long_march_8a,
    522 to Res.drawable.starship_v3,
    527 to Res.drawable.starship_v2,
)

fun rocketImage(configurationId: Int): DrawableResource = rocketImages.getValue(configurationId)
