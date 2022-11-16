package me.pzz.codechallengeluxsoft.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import me.pzz.codechallengeluxsoft.movie.data.Movie

@Composable
fun mainView(movieList: List<Movie>, addMovie: (String) -> Unit, remove : (String) -> Unit) {
    var movie_id_field by remember { mutableStateOf("") }

    Scaffold {
        Column(verticalArrangement = Arrangement.SpaceAround) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { addMovie(movie_id_field); movie_id_field = "" }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Movie"
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.width(230.dp),
                    value = movie_id_field,
                    onValueChange = { movie_id_field = it },
                    label = { Text("Movie ID") },
                    placeholder = { Text("Movie ID from TMDB") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = !movie_id_field.isDigitsOnly()
                )
                Button(onClick = { remove(movie_id_field); movie_id_field = ""}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Movie"
                    )
                }
            }
            LazyColumn(contentPadding = it) {
                items(movieList) {
                    movie(movie = it)
                }


                /*items(movieList) { movie ->
                    movie(movie) // Does no work correctlly, only shows first element
                }*/
            }
        }
    }
}