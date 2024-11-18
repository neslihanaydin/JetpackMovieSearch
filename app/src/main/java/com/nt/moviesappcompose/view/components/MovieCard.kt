package com.nt.moviesappcompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nt.moviesappcompose.model.Movie
import com.nt.moviesappcompose.ui.theme.Typography

@Composable
fun MovieCard(
    movie: Movie,
    //isClikable: Boolean = false,
    onClick: (movieId: String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .clip(
                shape = RoundedCornerShape(size = 15.dp)
            )
            .background(Color.Black)
            /*
            .then(
                if (isClikable) {
                    Modifier.clickable {
                        onClick(movie.imdbID)
                    }
                } else {
                    Modifier
                }

            )
             */
            .clickable {
                onClick(movie.imdbID)
            },
       // verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7F)
        ) {
            AsyncImage(
                model = movie.Poster,
                contentDescription = "${movie.Title} image",
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds(),
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier.padding(20.dp)
                .background(color = Color.Transparent)
        ) {
            Text(
                movie.Title,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,  // title ...
                color = Color.White,
                style = Typography.titleLarge
            )
        }
    }
}