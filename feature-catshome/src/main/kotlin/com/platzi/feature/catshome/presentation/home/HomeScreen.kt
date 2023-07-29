package com.platzi.feature.catshome.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.platzi.feature.catshome.domain.model.Cat
import com.platzi.feature.catshome.presentation.detail.CatDetailScreen
import com.platzi.feature.catshome.presentation.route.HomeRoutes
import com.platzi.feature.catshome.presentation.cats.CatsScreen
@Composable
fun HomeScreen(
    cats: LazyPagingItems<Cat>,
    navigationController: NavHostController,
) {

    NavHost(navController = navigationController, startDestination = HomeRoutes.ListScreen.route) {
        composable(HomeRoutes.ListScreen.route) {
            CatsScreen(cats)
        }
        composable(HomeRoutes.DetailScreen.route) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString(HomeRoutes.DetailScreen.ID) ?: ""
            CatDetailScreen(catId) {}
        }
    }
}