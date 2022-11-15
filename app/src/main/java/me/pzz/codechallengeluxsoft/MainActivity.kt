package me.pzz.codechallengeluxsoft

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import me.pzz.codechallengeluxsoft.movie.MoviesManager
import me.pzz.codechallengeluxsoft.ui.theme.CodeChallengeLuxsoftTheme
import me.pzz.luxoftcodechallenge.movie.Movie


class MainActivity : ComponentActivity() {

    val moviesManager by viewModels<MoviesManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeChallengeLuxsoftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                    color = MaterialTheme.colors.background

                ) {
                    Column{
                        movieList(movieList = moviesManager.movies) {moviesManager.addMovie()}
                    }
                }
            }
        }
    }
}


@Composable
fun movieList(movieList: List<Movie>, addMovie: () -> Unit) {
    Scaffold {
        Column(verticalArrangement = Arrangement.Bottom) {
            Button(onClick = addMovie) {
                Text(text = "Click Me")
            }
            LazyColumn (contentPadding = it) {
                items(movieList){
                    Text(text = it.title)
                    it.poster_path?.let { it1 -> Poster(posterURl = it1) }
                }
                items(movieList) { movie ->
                    movie(movie) // Does no work correctlly, only shows first element
                }
            }
        }
    }
}

@Composable
fun movie(movie : Movie){
    Card(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .height(2000.dp),
        //shape = RoundedCornerShape(8.dp),
        //elevation = 4.dp
    ) {
        Row {
            if(movie.poster_path != null){
                Poster(posterURl = movie.poster_path)
            } else {
            }
            Column {
                Text(text = movie.title)
                Text(text = movie.id.toString())
                Text(text = movie.genres.toString())
            }
        }
    }

}

@Composable
fun Poster(posterURl : String){
    Box(modifier = Modifier
        .height(150.dp)
        .padding(5.dp),
        contentAlignment = Alignment.Center) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original/$posterURl",
            contentDescription = "Poster Image",
        )
    }

}


@Composable
fun Greeting(name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodeChallengeLuxsoftTheme {
        Greeting("Android")
    }
}