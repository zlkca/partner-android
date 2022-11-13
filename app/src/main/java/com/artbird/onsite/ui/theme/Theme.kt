package com.artbird.onsite.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


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
        typography = typography,
        content = content
    )
}