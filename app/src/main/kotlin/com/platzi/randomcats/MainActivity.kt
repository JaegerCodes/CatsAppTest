package com.platzi.randomcats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import com.platzi.feature.catshome.presentation.home.CatViewModel
import com.platzi.feature.catshome.presentation.home.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val viewModel: CatViewModel = hiltViewModel()
                val cats = viewModel.catPagingFlow.collectAsLazyPagingItems()
                val catsState by viewModel.uiState.collectAsState()

                val navController = rememberNavController()
                HomeScreen(
                    cats = cats,
                    catDetailState = catsState,
                    navigationController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
