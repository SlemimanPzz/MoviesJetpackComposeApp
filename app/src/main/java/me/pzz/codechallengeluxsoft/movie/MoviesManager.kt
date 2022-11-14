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

class MoviesManager : ViewModel() {
    var movies : List<Movie> by mutableStateOf(mutableListOf())
    var errorMessage : String by mutableStateOf("")


    fun getMovies() {
        viewModelScope.launch {
            val apiService = TMDBApi.getInstance()
            try {
                val movie = apiService.getMovie("550", BuildConfig.API_KEY)
                movies = listOf(movie)
                Log.d("Movie", movie.toString())
                Log.i("Movie Info", movie.toString())
            } catch (e : Exception) {
                Log.d("Error","Aqui salio algo mal")
                e.message
            }
        }
    }
}