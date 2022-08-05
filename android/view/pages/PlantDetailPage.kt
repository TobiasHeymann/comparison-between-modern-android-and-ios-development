package com.tobiasheymann.ep.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.model.ContentType
import com.tobiasheymann.ep.model.Plant
import com.tobiasheymann.ep.view.components.*

@Composable
fun PlantDetailPage(navController: NavController) {

    if (navController.currentBackStackEntry?.arguments?.containsKey("plant") == true) {
        val plant = navController.currentBackStackEntry?.arguments?.getSerializable("plant") as Plant

        LazyColumn {
            item {
                Box {
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
                            .fillMaxWidth()
                            .height(256.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.background_curve),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .offset(y = 5.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.background)
                    )
                    Box(modifier = Modifier.padding(16.dp)) {
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
                                    modifier = Modifier
                                        .size(20.dp, 20.dp),
                                    tint = Color.White
                                )
                            }
                        )
                    }
                }

                Text(
                    text = plant.metadata.title,
                    modifier = Modifier.padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 2.dp),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = plant.metadata.subtitle,
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp, end = 24.dp, bottom = 24.dp),
                    style = MaterialTheme.typography.subtitle1
                )

                ContentFacts(facts = plant.facts)

                plant.content.forEach { c ->
                    when {
                        c.type === ContentType.HEADER -> {
                            ContentHeader(header = c.content)
                        }
                        c.type === ContentType.TEXT -> {
                            ContentText(text = c.content)
                        }
                        c.type === ContentType.IMAGE -> {
                            ContentImage(url = c.content)
                        }
                        c.type === ContentType.MAP -> {
                            ContentMap(id = c.content)
                        }
                    }
                }

                FooterComponent(navController = navController)
            }
        }
    }
}