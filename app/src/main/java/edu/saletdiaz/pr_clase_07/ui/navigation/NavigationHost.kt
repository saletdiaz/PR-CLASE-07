package edu.saletdiaz.pr_clase_07.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.saletdiaz.pr_clase_07.model.Editorial
import edu.saletdiaz.pr_clase_07.ui.screens.ComicScreen
import edu.saletdiaz.pr_clase_07.ui.screens.EditorialScreen


@Composable
fun NavigationHost(navController: NavHostController) {

    val context = LocalContext.current

    NavHost(
        navController =navController,
        startDestination = NavScreens.NavEditorialScreen.ruta
    ) {
        composable (NavScreens.NavEditorialScreen.ruta){
            EditorialScreen(
                onGoComic ={ navController.navigate(NavScreens.NavComicScreen.ruta)}
            )
        }
        composable (NavScreens.NavComicScreen.ruta){
            ComicScreen( /***/)
        }
    }
}