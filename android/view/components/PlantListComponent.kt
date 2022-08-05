package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.controller.view.navigate
import com.tobiasheymann.ep.model.Plant
import com.tobiasheymann.ep.view.NavDestination

@ExperimentalMaterialApi
@Composable
fun PlantRecommendationListComponent(navController: NavController, plants: List<Plant>) {
    LazyRow(
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = plants) { index, plant ->
            PlantRecommendationComponent(
                navController = navController,
                firstItem = index == 0,
                lastItem = index == plants.size - 1,
                plant = plant
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PlantRecommendationComponent(navController: NavController, firstItem: Boolean, lastItem: Boolean, plant: Plant) {
    Card(
        onClick = {
            navController.navigate(NavDestination.PLANT, bundleOf(Pair("plant", plant)))
        },
        backgroundColor = MaterialTheme.colors.secondary,
        indication = rememberRipple(color = MaterialTheme.colors.primary),
        modifier = Modifier
            .size(width = if (firstItem || lastItem) 216.dp else 192.dp, height = 256.dp)
            .padding(start = if (firstItem) 24.dp else 0.dp, end = if (lastItem) 24.dp else 0.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = plant.metadata.image,
                    builder = {
                        crossfade(true)
                        scale(Scale.FILL)
                        placeholder(drawableResId = R.drawable.placeholder_square_large)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 192.dp, height = 224.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(32.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = plant.metadata.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}