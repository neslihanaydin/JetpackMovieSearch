package com.nt.moviesappcompose.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nt.moviesappcompose.R

@Composable
fun PlaceholderImage() {
    Image(
        painter = painterResource(id = R.drawable.nomovie),
        contentDescription = "Placeholder image",
        modifier = Modifier
            .size(100.dp)
            .clip(
                shape = RoundedCornerShape(size = 15.dp)
            ),
        contentScale = ContentScale.Crop
    )
}