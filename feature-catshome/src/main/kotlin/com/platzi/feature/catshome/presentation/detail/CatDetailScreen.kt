package com.platzi.feature.catshome.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.platzi.feature.catshome.domain.model.Breed
import com.platzi.feature.catshome.domain.model.CatDetail
import com.platzi.feature.catshome.presentation.cats.CatsState

@Composable
fun CatDetailScreen(id: String, onBackClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        /*catDetail?.let {
            CatDetailHeader(onBackClicked)
            CatDetailContent(catDetail)
        }*/
    }
}

@Composable
fun CatDetailHeader(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text("Cat Detail") },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun CatDetailContent(catData: CatDetail) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = catData.url,
            contentDescription = "Cat Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        CardList(catData.breeds)
    }
}

@Composable
fun CardList(breeds: List<Breed>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(breeds) { breed ->
            CatCard(breed)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CatCard(breed: Breed) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = breed.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breed.description ?: "", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Origin: ${breed.origin ?: ""}", style = MaterialTheme.typography.body2)
        }
    }
}