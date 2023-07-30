package com.platzi.feature.catshome.presentation.route

sealed class HomeRoutes(val route: String) {
    object CatsScreen: HomeRoutes("cat_list")
    object DetailScreen: HomeRoutes("cat_detail?catId={catId}") {
        fun createRoute(catId: String) = "cat_detail?catId=$catId"
        const val ID = "catId"
    }
}