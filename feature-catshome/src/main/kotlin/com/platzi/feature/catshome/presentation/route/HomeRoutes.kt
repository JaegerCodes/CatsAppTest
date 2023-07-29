package com.platzi.feature.catshome.presentation.route

sealed class HomeRoutes(val route: String) {
    object ListScreen: HomeRoutes("cat_list")
    object DetailScreen: HomeRoutes("cat_detail") {
        fun createRoute(catId: String) = "cat_detail/$catId"
        const val ID = "catId"
    }
}