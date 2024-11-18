package com.nt.moviesappcompose.view.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nt.moviesappcompose.model.LoadingState
import com.nt.moviesappcompose.ui.theme.Typography
import com.nt.moviesappcompose.view.components.LoadingView
import com.nt.moviesappcompose.view.components.MovieCard
import com.nt.moviesappcompose.view.components.PlaceholderImage

@Composable
fun SearchField(
    viewModel: MovieViewViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
        ) {
        val uiState = viewModel.uiState.collectAsState()
        val text = uiState.value.searchText.value
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                viewModel.updateSearchText(it)
            },
            singleLine = true,
            placeholder = { Text("Search ...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { viewModel.updateSearchText("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                    }
                }
            }
        )
    }
}

@Composable
fun MovieView(
    viewModel: MovieViewViewModel = hiltViewModel(),
    onClick: (movieId: String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top
    ) {
        SearchField(viewModel)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            when(val loadingState = uiState.value.loadingState) {
                is LoadingState.Initial -> {
                    PlaceholderImage()
                    Text(
                        "Nothing here yet.\nStart typing a movie name to begin!",
                        style = Typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp),
                        textAlign = TextAlign.Center
                    )
                }
                is LoadingState.Success -> {
                    uiState.value.response?.let { searchResponse ->
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp)

                        ) {
                            item { // item for LazyColumn
                                searchResponse.Search.forEachIndexed { index, movie ->
                                    MovieCard(
                                        movie,
                                        onClick = onClick
                                    )
                                }
                            }
                        }
                    }
                }
                is LoadingState.Loading -> {
                    LoadingView()
                }
                is LoadingState.Error -> {
                    val errorString = loadingState.error
                    Text(errorString)
                }
            }
        }
    }
}