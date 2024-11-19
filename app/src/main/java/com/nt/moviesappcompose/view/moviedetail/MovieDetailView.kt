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
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
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
                        movie.informationRows(context).forEachIndexed { index, movie ->
                            InformationRow(title = movie.first, description = movie.second, index % 2 == 0, 80)
                        }
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