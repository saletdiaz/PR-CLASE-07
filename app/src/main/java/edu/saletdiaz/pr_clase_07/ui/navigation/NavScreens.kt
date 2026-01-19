package edu.saletdiaz.pr_clase_07.ui.navigation

/**Clase que contendrá las rutas de navegación de editorial y comic*/
sealed class NavScreens (val ruta: String) {
    object NavEditorialScreen: NavScreens("editorial")
    object NavComicScreen: NavScreens("comic/{editorialId}")
}