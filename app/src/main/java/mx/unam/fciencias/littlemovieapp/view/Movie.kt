package mx.unam.fciencias.littlemovieapp.view

import android.widget.Space
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.unam.fciencias.littlemovieapp.movie.data.Movie
import java.text.NumberFormat
import java.util.*

@Composable
fun movie(movie : Movie){
    val overviewScroll = rememberScrollState()

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Poster(posterURl = movie.poster_path)
                Column {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = movie.title.take(20),
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Row {
                            Text(text = "ID:", fontWeight = FontWeight.Light)
                            Text(
                                text = movie.id.toString(),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.W100,
                                fontStyle = FontStyle.Italic,
                                maxLines = 1
                            )
                        }
                    }
                    Divider()
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Release: " + movie.release_date)
                            Text("${movie.original_language}",
                                textAlign = TextAlign.End,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if(movie.runtime == null){
                                Text("Unknown Duration")
                            } else {
                                Text("Dur: ${movie.runtime} min")
                            }
                            Text("Usr score: %1.1f".format(movie.vote_average))
                        }
                        Text("Budget: $" + NumberFormat.getNumberInstance(Locale.US).format(movie.budget))
                        Text("Revenue: $" + NumberFormat.getNumberInstance(Locale.US).format(movie.revenue))
                    }
                    movie.overview?.let { Text("Overview: $it",
                        Modifier.scrollable(overviewScroll, orientation = Orientation.Vertical),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis) }

                }
            }
        }
    }

}