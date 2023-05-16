package mx.unam.fciencias.littlemovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.unam.fciencias.littlemovieapp.movie.data.*
import mx.unam.fciencias.littlemovieapp.movie.manager.MoviesManager
import mx.unam.fciencias.littlemovieapp.ui.theme.LittleMovieAppTheme
import mx.unam.fciencias.littlemovieapp.view.mainView
import mx.unam.fciencias.littlemovieapp.view.movie


class MainActivity : ComponentActivity() {

    val moviesManager by viewModels<MoviesManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    mainView(movieList = moviesManager.movies, {moviesManager.addMovies(it)}, {moviesManager.removeMovie(it)}, {moviesManager.sort(it)})
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittleMovieAppTheme {
        movie(movie = Movie(
            budget = 1000,
            genres = listOf(Genre(1, "Testing")),
            homepage = "https://localhost",
            id = 550,
            original_language = "es",
            original_title = "Test",
            overview = "Testong overview of a movie.",
            poster_path = null,
            production_companies = listOf(ProductionCompany(1, null, "Testing Productions", "Mexico")),
            production_countries = listOf(ProductionCountry("mx", "Mexico")),
            release_date = "2022-11-15",
            revenue = 1000,
            runtime = 120,
            spoken_languages = listOf(SpokenLanguage("es","Spanish")),
            status = "ok",
            title = "Testing Movie",
            vote_average = 7.5,
            vote_count = 30
        )
        )
    }
}