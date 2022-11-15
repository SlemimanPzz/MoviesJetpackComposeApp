package me.pzz.codechallengeluxsoft.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import me.pzz.luxoftcodechallenge.movie.Movie
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.pzz.codechallengeluxsoft.BuildConfig
import java.lang.Exception
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class MoviesManager : ViewModel() {
    var movies  by mutableStateOf(listOf<Movie>())
    var errorMessage : String by mutableStateOf("")


    fun getMultipleMovies() {
        val ids = arrayOf("550", "55", "79")
        viewModelScope.launch {
            val apiService = TMDBApi.getInstance()
            val lista_de = mutableListOf<Movie>()
            for(id in ids) {
                try {
                    val movie = apiService.getMovie(id, BuildConfig.API_KEY)
                    lista_de.add(movie)
                    Log.d("Movie", movie.toString())
                    Log.i("Movie Info", movie.toString())
                } catch (e : Exception) {
                    Log.d("Error","Aqui salio algo mal")
                    e.message
                }
            }
            movies = lista_de
        }
    }

    fun addMovie(movie_id : String = "550") {
        viewModelScope.launch {
            val apiService = TMDBApi.getInstance()
            try {
                val movie = apiService.getMovie( movie_id, BuildConfig.API_KEY)
                var temp = movies.toMutableList()
                temp.add(movie)
                movies = temp
                Log.d("Movie", movie.toString())
                Log.i("Movie Info", movie.toString())
            } catch (e : Exception) {
                Log.d("Error","Aqui salio algo mal")
                e.message
            }
        }
    }
}