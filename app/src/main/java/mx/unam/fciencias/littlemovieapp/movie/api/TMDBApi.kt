package mx.unam.fciencias.littlemovieapp.movie.api

import mx.unam.fciencias.littlemovieapp.movie.data.Movie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import  retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movie_id : String, @Query("api_key") api_key : String) : Movie


    companion object {
        var TmdbApi : TMDBApi? = null
        fun getInstance() : TMDBApi {
            if (TmdbApi == null) {
                TmdbApi = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build().create(TMDBApi::class.java)
            }
            return TmdbApi!!
        }
    }
}