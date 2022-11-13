package com.artbird.onsite.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

//val primary_main = Color(0x2A5135FF)
//val onPrimary = Color(0xFFFFFFFF)
//
//val secondary_main = Color(0xA07D2DFF)
//val onSecondary = Color(0xFFFFFFFF)


//val md_theme_light_primary = Color(0xFF476810)
//val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFC7F089)
// ..
// ..

//val md_theme_dark_primary = Color(0xFFACD370)
//val md_theme_dark_onPrimary = Color(0xFF213600)
val md_theme_dark_primaryContainer = Color(0xFF324F00)
// ..
// ..


val primary_main = Color(42,81,53) // 0x2A5135FF)
val primary_dark = Color(44, 77, 55)
val primary_light = Color(110, 128, 119) // 0x6E8077

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

val LightColors = lightColorScheme(
    primary = primary_main, // selected list item
    onPrimary = onPrimary,

//    primaryContainer = primary_light, // unselected list item, input
//    onPrimaryContainer = black,

    secondary = secondary_main,
    onSecondary = onSecondary,

    background = backgroundLight,
    onBackground = onBackgroundLight,

//    surface = primary_light,

// ..
)

val DarkColors = darkColorScheme(
    primary = primary_main,
    onPrimary = onPrimary,

//    primaryContainer = darkBlack,
//    onPrimaryContainer = white,

    secondary = secondary_main,
    onSecondary = onSecondary,

    onBackground = onBackgroundDark,

//    surface = primary_light,
// ..
)