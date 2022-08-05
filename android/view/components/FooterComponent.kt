package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tobiasheymann.ep.view.NavDestination

@Composable
fun FooterComponent(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(top = 64.dp, bottom = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            navController.navigate(NavDestination.ABOUT)
        }) {
            Text(
                text = "Impressum",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h2
            )
        }
        Text(
            text = "© Tobias Heymann",
            style = MaterialTheme.typography.h2
        )
    }
}