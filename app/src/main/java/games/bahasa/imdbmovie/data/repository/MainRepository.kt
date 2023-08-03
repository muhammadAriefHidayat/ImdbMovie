package games.bahasa.imdbmovie.data.repository

import games.bahasa.imdbmovie.data.datastore.RemoteDataSource


class MainRepository(
    private val remoteDataSource: RemoteDataSource
) {
    fun getTrendingMovies() = remoteDataSource.getTrendingMovies()
    fun getTrendingTv() = remoteDataSource.getTrendingTv()
    fun getImagesTv(id:Int) = remoteDataSource.getImagesTv(id)
    fun getSearchMovie(query:String) = remoteDataSource.getSearchMovie(query)
    fun getSearchTv(query:String) = remoteDataSource.getSearchTv(query)

}