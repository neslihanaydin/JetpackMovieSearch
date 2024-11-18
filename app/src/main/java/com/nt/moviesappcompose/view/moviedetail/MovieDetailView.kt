package com.nt.moviesappcompose.view.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.nt.moviesappcompose.model.LoadingState
import com.nt.moviesappcompose.view.components.InformationRow
import com.nt.moviesappcompose.view.components.LoadingView

@Composable
fun MovieDetailView(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(movieId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val loadingState = uiState.value.loadingState) {
            is LoadingState.Success -> {
                val movie = uiState.value.response
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (movie != null) {
                        AsyncImage(
                            movie.Poster,
                            contentDescription = movie.Title + " poster",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.65F),
                            contentScale = ContentScale.FillBounds
                        )
                        InformationRow(title = "Title", description = movie.Title, true)
                        InformationRow(title = "Rating", description = movie.imdbRating, false)
                        InformationRow(title = "Released", description = movie.Released, true)
                        InformationRow(title = "Director", description = movie.Director, false)
                        InformationRow(title = "Genre", description = movie.Genre, true)
                        InformationRow(title = "Runtime", description = movie.Runtime, false)
                        InformationRow(title = "Plot", description = movie.Plot, true)
                    }
                }
            }
            is LoadingState.Loading -> {
                LoadingView()
            }
            else -> {
                Text("Error")
            }
        }
    }
}