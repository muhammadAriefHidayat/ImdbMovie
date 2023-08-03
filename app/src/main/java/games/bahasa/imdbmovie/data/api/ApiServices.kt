package games.bahasa.imdbmovie.data.api


import games.bahasa.imdbmovie.model.ImagesBackdrop
import games.bahasa.imdbmovie.model.Movies
import games.bahasa.imdbmovie.model.Tv
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("3/trending/movie/day")
    fun getTrendingMovies(
    ): Call<Movies>

    @GET("/3/search/movie")
    fun getSearchMovie(
        @Query("query") query: String
    ): Call<Movies>

    @GET("3/trending/tv/day")
    fun getTrendingTv(
    ): Call<Tv>

    @GET("3/tv/{id}/images")
    fun getImagesTv(@Path("id") id: Int
    ): Call<ImagesBackdrop>

    @GET("3/trending/tv/day")
    fun searchTv(
    ): Call<Movies>

    @GET("3/search/tv")
    fun getSearchTv(
        @Query("query") query: String
    ): Call<Tv>

}