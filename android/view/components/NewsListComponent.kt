package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.controller.view.navigate
import com.tobiasheymann.ep.model.News
import com.tobiasheymann.ep.view.NavDestination
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalMaterialApi
@Composable
fun NewsListComponent(navController: NavController, news: List<News>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        news.forEach {
            NewsComponent(navController, it)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NewsComponent(navController: NavController, news: News) {
    Card(
        onClick = {
            navController.navigate(NavDestination.NEWS, bundleOf(Pair("news", news)))
        },
        indication = rememberRipple(color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = news.metadata.image,
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

                val dateTimeFormatter = DateTimeFormatter
                    .ofPattern("dd. MMMM yyyy")
                    .withLocale(Locale.GERMANY)
                    .withZone(ZoneId.systemDefault())

                Text(
                    text = news.metadata.title,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = dateTimeFormatter.format(news.metadata.timestamp),
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = news.firstText,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
