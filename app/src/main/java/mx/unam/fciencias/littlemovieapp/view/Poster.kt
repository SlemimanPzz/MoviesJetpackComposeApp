package mx.unam.fciencias.littlemovieapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Poster(posterURl : String?){
    Box(
        modifier = Modifier
            .width(140.dp)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        if (posterURl == null) {
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Warning, contentDescription = "No Image found")
                Text("Poster not found")
            }
        } else {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original/$posterURl",
                contentDescription = "Poster Image"
            )
        }
    }

}