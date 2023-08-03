package games.bahasa.imdbmovie.data.api


import games.bahasa.imdbmovie.model.Movies
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("3/trending/movie/day")
    fun getTrendingMovies(
    ): Call<Movies>

}