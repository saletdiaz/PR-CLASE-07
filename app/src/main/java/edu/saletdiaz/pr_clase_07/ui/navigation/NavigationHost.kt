package edu.saletdiaz.pr_clase_07.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.saletdiaz.pr_clase_07.MainViewModel
import edu.saletdiaz.pr_clase_07.model.Editorial
import edu.saletdiaz.pr_clase_07.ui.screens.ComicScreen
import edu.saletdiaz.pr_clase_07.ui.screens.EditorialScreen


@Composable
fun NavigationHost(navController: NavHostController) {

    val vm: MainViewModel = viewModel()

    NavHost(
        navController =navController,
        startDestination = NavScreens.NavEditorialScreen.ruta
    ) {
        /**Pantalla de editorial*/
        composable (NavScreens.NavEditorialScreen.ruta){
            EditorialScreen(
                vm,
                onEditorialClick =  { id ->
                    navController.navigate("comic/$id")
                }
            )
        }
        /**Pantalla comic
        composable (NavScreens.NavComicScreen.ruta){ backStackEntry ->
            val editorialId = backStackEntry.arguments?.getString("editorialId")?.toInt() ?: 0
            ComicScreen(
                idEditorial = editorialId,
                viewModel  = vm,
                onBack = {
                    navController.popBackStack()
                }
            )
        }          */
        composable(NavScreens.NavComicScreen.ruta,
            listOf(
                navArgument("editorialId") {type = NavType.IntType}
            )
        )  { backStackEntry ->
            val editorialId = backStackEntry.arguments?.getInt("editorialId")?: 0
            ComicScreen(
                idEditorial = editorialId,
                viewModel = vm,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}