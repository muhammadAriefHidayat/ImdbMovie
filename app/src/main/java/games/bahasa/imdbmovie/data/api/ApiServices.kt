package games.bahasa.imdbmovie.data.api


import games.bahasa.imdbmovie.model.Movies
import games.bahasa.imdbmovie.model.Tv
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("3/trending/movie/day")
    fun getTrendingMovies(
    ): Call<Movies>

    @GET("3/trending/tv/day")
    fun getTrendingTv(
    ): Call<Tv>

}