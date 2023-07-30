package com.platzi.feature.catshome.presentation.route

sealed class HomeRoutes(val route: String) {
    object CatsScreen: HomeRoutes("cat_list")
    object DetailScreen: HomeRoutes("cat_detail")
}