package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Grass
import androidx.compose.material.icons.rounded.Height
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.TravelExplore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.model.Fact
import com.tobiasheymann.ep.model.FactIconType

@Composable
fun ContentHeader(header: String) {
    Text(
        text = header,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
        style = MaterialTheme.typography.h2
    )
}

@Composable
fun ContentText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun ContentImage(url: String) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                scale(Scale.FILL)
                placeholder(drawableResId = R.drawable.placeholder_square_large)
            }
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth()
            .height(192.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    )
}

@Composable
fun ContentMap(id: String) {

    when (id) {
        "tropical_zone" -> {
            Image(
                painter = rememberImagePainter(
                    data = "./climate_zone_tropical_zone.png",
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(224.dp)
            )
        }
        "tropical_and_subtropical_zone" -> {
            Image(
                painter = rememberImagePainter(
                    data = "./climate_zone_tropical_and_subtropical_zone.png",
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(224.dp)
            )
        }
        "temperate_zone" -> {
            Image(
                painter = rememberImagePainter(
                    data = "./climate_zone_temperate_zone.png",
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(224.dp)
            )
        }
        "subtropical_zone" -> {
            Image(
                painter = rememberImagePainter(
                    data = "./climate_zone_subtropical_zone.png"
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(224.dp)
            )
        }
        "polar_and_subpolar_zone" -> {
            Image(
                painter = rememberImagePainter(
                    data = "./climate_zone_polar_and_subpolar_zone.png",
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(224.dp)
            )
        }
    }
}

@Composable
fun ContentFacts(facts: List<Fact>) {

    LazyRow(
        modifier = Modifier.padding(bottom = 24.dp),
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = facts) { index, fact ->

            val firstItem = index == 0
            val lastItem = index == facts.size - 1

            Card(
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(width = if (firstItem || lastItem) 120.dp else 96.dp, height = 96.dp)
                    .padding(start = if (firstItem) 24.dp else 0.dp, end = if (lastItem) 24.dp else 0.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = getIconFrom(fact.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .rotate(if (fact.icon == FactIconType.WIDTH) 90F else 0F),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        text = fact.text,
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}

fun getIconFrom(iconType: FactIconType): ImageVector {
    return when (iconType) {
        FactIconType.HEIGHT -> Icons.Rounded.Height
        FactIconType.WIDTH -> Icons.Rounded.Height
        FactIconType.THERMOSTAT -> Icons.Rounded.Thermostat
        FactIconType.TRAVEL_EXPLORE -> Icons.Rounded.TravelExplore
        else -> Icons.Rounded.Grass
    }
}