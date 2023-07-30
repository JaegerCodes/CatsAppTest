package com.platzi.feature.catshome.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.platzi.feature.catshome.domain.model.Cat
import com.platzi.feature.catshome.presentation.route.HomeRoutes
import com.platzi.feature.catshome.presentation.home.cats.CatsScreen
import com.platzi.feature.catshome.presentation.home.detail.CatDetailScreen

@Composable
fun HomeScreen(
    cats: LazyPagingItems<Cat>,
    catsState: CatsState,
    loadCatDetail: (String) -> Unit,
    navigationController: NavHostController,
) {
    NavHost(navController = navigationController, startDestination = HomeRoutes.CatsScreen.route) {
        composable(HomeRoutes.CatsScreen.route) {
            CatsScreen(cats, navigationController)
        }
        composable(
            route = HomeRoutes.DetailScreen.route,
            arguments = listOf(navArgument(HomeRoutes.DetailScreen.ID) { defaultValue = "" })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString(HomeRoutes.DetailScreen.ID).orEmpty()
            CatDetailScreen(
                catsState,
                loadCatDetail = { loadCatDetail(catId) },
                onBackClicked = { navigationController.popBackStack() }
            )
        }
    }
}
