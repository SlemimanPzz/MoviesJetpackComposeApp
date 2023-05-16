package mx.unam.fciencias.littlemovieapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import mx.unam.fciencias.littlemovieapp.movie.data.Movie
import mx.unam.fciencias.littlemovieapp.movie.manager.SortFields

fun verifyInput(input : String) : Boolean{
    if(input == ""){
        return false
    }
    for (component in input.split(",")){
        if(!component.isDigitsOnly()) return true
    }
    return false
}

@Composable
fun bottomBar(){

}


@Composable
fun mainView(movieList: List<Movie>, addMovie: (String) -> Unit, remove : (String) -> Unit, sort : (Comparator<in Movie>) -> Unit) {
    var movieIdField by remember { mutableStateOf("") }
    var mExpanted by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Movies App")}) },
    ) {
        Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.padding(10.dp)) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { addMovie(movieIdField); movieIdField = "" }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Movie"
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.width(230.dp),
                    value = movieIdField,
                    onValueChange = { movieIdField = it },
                    label = { Text("Movie ID") },
                    placeholder = { Text("Movie ID from TMDB") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = verifyInput(movieIdField)
                )
                Button(onClick = { remove(movieIdField); movieIdField = ""}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Movie"
                    )
                }
            }



            /*DropdownMenu(expanded = mExpanted, onDismissRequest = { mExpanted = false }) {
                sortFields.values().forEach {
                    DropdownMenuItem(onClick = { sort(it.getComparator()) }) {
                        Text(it.toString())
                    }
                }
            }*/

            Row (verticalAlignment = Alignment.CenterVertically, ){
                Text(text = "Sort By:")

                LazyRow {
                    items(SortFields.values().toList()) {
                        Button(modifier = Modifier.padding(5.dp),onClick = {sort(it.getComparator())}) {
                            Text(it.toString())
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                }
            }



            LazyColumn(contentPadding = it) {
                items(movieList) {
                    movie(movie = it)
                }
            }

        }
    }
}