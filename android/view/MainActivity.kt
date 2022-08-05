package com.tobiasheymann.ep.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tobiasheymann.ep.view.pages.*
import com.tobiasheymann.ep.view.theme.EPTheme
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            EPTheme {
                NavHost(navController = navController, startDestination = NavDestination.HOME) {
                    composable(NavDestination.HOME) {
                        HomePage(navController = navController)
                    }
                    composable(NavDestination.ABOUT) {
                        AboutPage(navController = navController)
                    }
                    composable(NavDestination.NEWS) {
                        NewsDetailPage(navController = navController)
                    }
                    composable(NavDestination.PLANT) {
                        PlantDetailPage(navController = navController)
                    }
                    composable(NavDestination.QR) {
                        QRPage(navController = navController)
                    }
                }
            }
        }
    }
}

class NavDestination {
    companion object {
        const val HOME = "home"
        const val ABOUT = "about"
        const val NEWS = "news"
        const val PLANT = "plant"
        const val QR = "qr"
    }
}
