package com.tobiasheymann.ep.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tobiasheymann.ep.view.components.ContentHeader
import com.tobiasheymann.ep.view.components.ContentText
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AboutPage(navController: NavController) {
    LazyColumn {
        item {
            /* Header */
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colors.primary)
                        .size(32.dp, 32.dp),
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp, 20.dp),
                            tint = Color.White
                        )
                    }
                )
                Text(
                    text = "Impressum",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h1
                )
            }

            /* About */
            ContentHeader(header = "Adresse")
            Text(
                text = "Tobias Heymann",
                modifier = Modifier.padding(start = 24.dp, top = 6.dp, end = 24.dp)
            )
            Text(
                text = "Musterstraße 1",
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Text(
                text = "Musterstadt",
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Divider(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp)
            )

            /* Open Source Licences */
            Text(
                text = "Open Source Lizenzen",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                style = MaterialTheme.typography.h1
            )

            ContentHeader(header = "Accompanist")
            ContentText(
                text = "Copyright 2020 The Android Open Source Project\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at\n" +
                        "\n" +
                        "https://www.apache.org/licenses/LICENSE-2.0\n" +
                        "\n" +
                        "Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License."
            )

            ContentHeader(header = "Coil")
            ContentText(
                text = "Copyright 2021 Coil Contributors\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at\n" +
                        "\n" +
                        "https://www.apache.org/licenses/LICENSE-2.0\n" +
                        "\n" +
                        "Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License."
            )
        }
    }
}