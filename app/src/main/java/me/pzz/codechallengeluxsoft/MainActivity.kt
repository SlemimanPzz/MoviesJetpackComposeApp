package me.pzz.codechallengeluxsoft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.pzz.codechallengeluxsoft.movie.MoviesManager
import me.pzz.codechallengeluxsoft.ui.theme.CodeChallengeLuxsoftTheme
import me.pzz.luxoftcodechallenge.movie.Movie

class MainActivity : ComponentActivity() {


    val api = BuildConfig.API_KEY

    val moviesManager by viewModels<MoviesManager>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeChallengeLuxsoftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column{
                        Greeting(api)
                        movieList(movieList = moviesManager.movies)
                    }
                    moviesManager.getMovies()
                }
            }
        }
    }
}


@Composable
fun movieList(movieList: List<Movie>){
    LazyColumn {
        items(movieList) { movie ->
            movie(movie)
        }
    }
}

@Composable
fun movie(movie : Movie){
    Text(text = movie.title)
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