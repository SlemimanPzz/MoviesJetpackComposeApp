package mx.unam.fciencias.littlemovieapp.movie.manager

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import mx.unam.fciencias.littlemovieapp.BuildConfig
import mx.unam.fciencias.littlemovieapp.movie.api.TMDBApi
import mx.unam.fciencias.littlemovieapp.movie.data.Movie
import kotlin.Comparator


class MoviesManager : ViewModel() {
    var movies by mutableStateOf(listOf<Movie>())
    var errorMessage : String by mutableStateOf("")


    fun sort(comparator: Comparator<in Movie>){
        movies = movies.sortedWith(comparator)
    }

    fun removeMovie(movie_ids: String){
        viewModelScope.launch {
            for(id in movie_ids.split(",")){
                movies = movies.filter { movie ->
                    movie.id.toString() != id.trim()
                }
            }
        }
    }

    fun addMovies(movie_ids : String = "7459, 414906, 680, 120, 121, 122, 10501, 9837, 152601, 105, 813") {
        viewModelScope.launch {
            val apiService = TMDBApi.getInstance()
            val ids = movie_ids.split(",")
            for(id in ids){
                try {
                    val movie = apiService.getMovie( id.trim(), BuildConfig.api_key)
                    var temp = movies.toMutableList()
                    temp.add(movie)
                    movies = temp
                    Log.d("Movie", movie.toString())
                    Log.i("Movie Info", movie.toString())
                } catch (e : Exception) {
                    Log.d("Error","Aqui salio algo mal")
                    e.message
                }
                Thread.sleep(100)
            }

        }
    }
}