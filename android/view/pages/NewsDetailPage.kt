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
import com.tobiasheymann.ep.model.News
import com.tobiasheymann.ep.view.components.ContentHeader
import com.tobiasheymann.ep.view.components.ContentImage
import com.tobiasheymann.ep.view.components.ContentText
import com.tobiasheymann.ep.view.components.FooterComponent
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun NewsDetailPage(navController: NavController) {

    if (navController.currentBackStackEntry?.arguments?.containsKey("news") == true) {
        val news = navController.currentBackStackEntry?.arguments?.getSerializable("news") as News

        LazyColumn {
            item {
                Box {
                    Image(
                        painter = rememberImagePainter(
                            data = news.metadata.image,
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

                val dateTimeFormatter = DateTimeFormatter
                    .ofPattern("dd. MMMM yyyy")
                    .withLocale(Locale.GERMANY)
                    .withZone(ZoneId.systemDefault())
                Text(
                    text = news.metadata.title,
                    modifier = Modifier.padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 2.dp),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = dateTimeFormatter.format(news.metadata.timestamp),
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp, end = 24.dp, bottom = 24.dp),
                    style = MaterialTheme.typography.subtitle1
                )

                news.content.forEach { c ->
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
                    }
                }

                FooterComponent(navController = navController)
            }
        }
    }
}