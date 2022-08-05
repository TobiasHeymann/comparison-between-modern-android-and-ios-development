package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderComponent(header: String) {
    Text(
        text = header,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
        style = MaterialTheme.typography.h1
    )
}