package com.artbird.onsite.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Title2(text: String, color: Color =colorScheme.onBackground){
    Text(
        text = text,
        color = color,
        style = typography.titleMedium,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun Label2(text: String){
    Text(
        text = text,
        color = colorScheme.onBackground,
        style = typography.labelMedium
    )
}

@Composable
fun Label3(text: String){
    Text(
        text = text,
        color = colorScheme.onBackground,
        style = typography.labelSmall
    )
}

@Composable
fun Body1(text: String, color: Color= colorScheme.onBackground){
    Text(
        text = text,
        color = color,
        style = typography.bodyLarge,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}
@Composable
fun Body2(text: String, color: Color= colorScheme.onBackground,
          modifier: Modifier = Modifier.padding(bottom = 10.dp)
){
    Text(
        text = text,
        color = color,
        style = typography.bodyMedium,
        modifier = modifier
    )
}
@Composable
fun Body3(
    text: String,
    color: Color= colorScheme.onBackground,
    modifier: Modifier = Modifier.padding(bottom = 10.dp)
){
    Text(
        text = text,
        color = color,
        style = typography.bodySmall,
        modifier = modifier
    )
}