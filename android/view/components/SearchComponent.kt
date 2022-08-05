package com.tobiasheymann.ep.view.components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.controller.view.navigate
import com.tobiasheymann.ep.model.Plant
import com.tobiasheymann.ep.view.NavDestination

@Composable
fun SearchComponent(navController: NavController, text: String, onTextChange: (String) -> Unit) {
    val localFocusManager = LocalFocusManager.current
    val localContext = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            navController.navigate(NavDestination.QR)
        } else {
            Toast.makeText(localContext, "Für diese Funktion braucht die App Zugang zur Kamera", Toast.LENGTH_LONG).show()
        }
    }

    Card(
        modifier = Modifier.padding(
            start = 24.dp,
            top = 24.dp,
            end = 24.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    onTextChange("")
                    localFocusManager.clearFocus()
                },
                modifier = Modifier.size(32.dp, 32.dp),
                content = {
                    Icon(
                        imageVector = if (text.isEmpty()) Icons.Rounded.Search else Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp, 20.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            )
            EditFieldComponent(
                modifier = Modifier.weight(1f),
                value = text,
                onValueChange = onTextChange,
                placeholder = "Suchen ...",
            )
            IconButton(
                onClick = {
                    when (PackageManager.PERMISSION_GRANTED) {
                        ContextCompat.checkSelfPermission(
                            localContext,
                            Manifest.permission.CAMERA
                        ) -> {
                            navController.navigate(NavDestination.QR)
                        }
                        else -> {
                            launcher.launch(Manifest.permission.CAMERA)
                        }
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.primary)
                    .size(32.dp, 32.dp),
                content = {
                    Icon(
                        imageVector = Icons.Rounded.QrCode,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp, 20.dp),
                        tint = Color.White
                    )
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SearchResultListComponent(navController: NavController, plants: List<Plant>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        plants.forEach {
            SearchResultComponent(navController, it)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SearchResultComponent(navController: NavController, plant: Plant) {
    Card(
        onClick = {
            navController.navigate(NavDestination.PLANT, bundleOf(Pair("plant", plant)))
        },
        indication = rememberRipple(color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = plant.metadata.image,
                    builder = {
                        crossfade(true)
                        scale(Scale.FILL)
                        placeholder(drawableResId = R.drawable.placeholder_square)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(116.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            ) {

                Text(
                    text = plant.metadata.title,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = plant.metadata.subtitle,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = plant.firstText,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}