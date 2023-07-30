package com.platzi.feature.catshome.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.platzi.feature.catshome.domain.model.Cat
import com.platzi.feature.catshome.presentation.route.HomeRoutes
import com.platzi.feature.catshome.presentation.home.cats.CatsScreen
import com.platzi.feature.catshome.presentation.home.detail.CatDetailScreen
import com.platzi.feature.catshome.presentation.home.detail.CatDetailState

@Composable
fun HomeScreen(
    cats: LazyPagingItems<Cat>,
    catDetailState: CatDetailState,
    navigationController: NavHostController,
    viewModel: CatViewModel
) {
    LaunchedEffect(catDetailState) {
        if (catDetailState is CatDetailState.Detail) {
            navigationController.navigate(HomeRoutes.DetailScreen.route)
        }
    }

    NavHost(
        navController = navigationController,
        startDestination = HomeRoutes.CatsScreen.route
    ) {
        composable(HomeRoutes.CatsScreen.route) {
            CatsScreen(cats, viewModel, catDetailState)
        }
        composable(
            route = HomeRoutes.DetailScreen.route,
        ) {
            CatDetailScreen(viewModel, onBackClicked = { navigationController.popBackStack() })
        }
    }
}