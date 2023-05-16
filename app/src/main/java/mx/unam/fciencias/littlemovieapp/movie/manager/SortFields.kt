package mx.unam.fciencias.littlemovieapp.movie.manager


import mx.unam.fciencias.littlemovieapp.movie.data.Movie

enum class SortFields{
    ID,
    TITLE,
    RELEASE_DATE,
    REVENUE,
    RUNTIME,
    SCORE,
    BUDGET;


    override fun toString() : String{
        return when(this){
            ID -> "ID"
            TITLE -> "Title"
            RELEASE_DATE -> "Release Date"
            REVENUE -> "Revenue"
            RUNTIME -> "Runtime"
            SCORE -> "Score"
            BUDGET -> "Budget"
        }
    }

    fun getComparator() : Comparator<Movie> {
        return when(this) {
            ID -> compareBy {it.id}
            TITLE -> compareBy {it.title}
            RELEASE_DATE -> compareBy {it.release_date /* TODO */ }
            REVENUE -> compareBy  { it.revenue }
            RUNTIME -> compareBy { it.runtime }
            SCORE -> compareByDescending { it.runtime }
            BUDGET -> compareBy { it.budget }
        }
    }
}