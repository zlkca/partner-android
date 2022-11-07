package com.artbird.onsite.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primary_main = Color(42,81,53) // 0x2A5135FF)
val primary_dark = Color(44, 77, 55)
val primary_light = Color(0xe2e9e4) // Color(110, 159, 135)

val onPrimary = Color(0xFFFFFFFF)

val secondary_main = Color(160, 125,45) // 0xA07D2DFF)
val secondary_light = Color(175, 146, 76)
val onSecondary = Color(0xFFFFFFFF)

val backgroundLight = Color(0xFFFFFFFF)
val onBackgroundLight = Color(77,77,77)
var onBackgroundDark = Color(0xFFFFFFFF)

val white = Color(0xFFFFFFFF)
val black = Color(0x000000)
var darkBlack = Color(0xddd)
private val LightColors = lightColorScheme(
    primary = primary_main, // selected list item
    onPrimary = onPrimary,

//    primaryContainer = primary_light, // unselected list item, input
//    onPrimaryContainer = black,

    secondary = secondary_main,
    onSecondary = onSecondary,

    background = backgroundLight,
    onBackground = onBackgroundLight,

    surface = primary_light,

// ..
)
private val DarkColors = darkColorScheme(
    primary = primary_main,
    onPrimary = onPrimary,

//    primaryContainer = darkBlack,
//    onPrimaryContainer = white,

    secondary = secondary_main,
    onSecondary = onSecondary,

    onBackground = onBackgroundDark,

// ..
)

@Composable
fun SLTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme =
        if (!useDarkTheme) {
            LightColors
        } else {
            DarkColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}