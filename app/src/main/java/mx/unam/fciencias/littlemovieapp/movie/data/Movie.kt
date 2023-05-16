package mx.unam.fciencias.littlemovieapp.movie.data


data class Movie(
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
