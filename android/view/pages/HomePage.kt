package com.tobiasheymann.ep.view.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.tobiasheymann.ep.model.News
import com.tobiasheymann.ep.model.Plant
import com.tobiasheymann.ep.model.services.DatabaseService
import com.tobiasheymann.ep.view.components.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun HomePage(navController: NavController) {

    //State
    var searchedTerm by rememberSaveable { mutableStateOf("") }
    var availableNews by rememberSaveable { mutableStateOf(listOf<News>()) }
    var availablePlants by rememberSaveable { mutableStateOf(listOf<Plant>()) }
    var searchedPlants by rememberSaveable { mutableStateOf(listOf<Plant>()) }

    //Action
    downloadNews { availableNews = it }
    downloadPlants { availablePlants = it }

    //Layout
    LazyColumn {
        item {
            SearchComponent(navController = navController, searchedTerm) { needle ->
                searchedTerm = needle

                executeSearch(needle) { results ->
                    searchedPlants = results
                }
            }
        }

        if (searchedTerm.isEmpty()) {
            item {
                HeaderComponent(header = "Pflanzen der Woche")
                PlantRecommendationListComponent(navController = navController, plants = availablePlants)
                HeaderComponent(header = "Neuigkeiten")
                NewsListComponent(navController = navController, news = availableNews)
            }
        } else {
            item {
                HeaderComponent(header = "Ergebnisse")
                SearchResultListComponent(navController = navController, plants = searchedPlants)
            }
        }

        item {
            FooterComponent(navController)
        }
    }

}

fun downloadNews(onResult: (List<News>) -> Unit) {
    DatabaseService.getLastTenNews { result ->
        result?.let { news ->
            onResult(news)
        }
    }
}

fun downloadPlants(onResult: (List<Plant>) -> Unit) {
    DatabaseService.getLastFivePlants { result ->
        result?.let { plant ->
            onResult(plant)
        }
    }
}

@DelicateCoroutinesApi
fun executeSearch(needle: String, onResult: (List<Plant>) -> Unit) {
    if (needle.length > 2) {
        DatabaseService.getAllPlants() { plants ->
            onResult(plants.filter { plant -> plant.metadata.title.lowercase().startsWith(needle.lowercase()) })
        }
    } else {
        onResult(listOf())
    }
}