package com.nt.moviesappcompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nt.moviesappcompose.ui.theme.MoviesAppComposeTheme

@Composable
fun InformationRow(
    title: String,
    description: String,
    changeBackgroundColor: Boolean = false,
    titleWidth: Int
){
    val textColor = if (!changeBackgroundColor) Color.White else Color.Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (changeBackgroundColor) {
                    Modifier.background(Color(0xFFFF9800))
                } else {
                    Modifier.background(Color.Transparent)
                })
            .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "$title ",
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier
                .width(titleWidth.dp),
        )
        Text(
            "$description",
            color = textColor,
            modifier = Modifier
                .padding(start = 10.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesAppComposeTheme {
        //InformationRow("Released", "Some Description about movie, Description about movie, Description about movie, Description about movie,")
        InformationRow("Year", "2222", true, 29)
    }
}