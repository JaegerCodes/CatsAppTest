package com.platzi.randomcats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.platzi.feature.catshome.presentation.CatScreen
import dagger.hilt.android.AndroidEntryPoint
import com.platzi.feature.catshome.presentation.CatViewModel
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val viewModel = hiltViewModel<CatViewModel>()
                val cats = viewModel.catPagingFlow.collectAsLazyPagingItems()
                CatScreen(cats = cats)
            }
        }
    }
}