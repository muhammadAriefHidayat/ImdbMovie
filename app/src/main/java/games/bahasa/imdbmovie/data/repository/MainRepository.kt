package games.bahasa.imdbmovie.data.repository

import games.bahasa.imdbmovie.data.datastore.RemoteDataSource


class MainRepository(
    private val remoteDataSource: RemoteDataSource
) {
    fun getTrendingMovies() = remoteDataSource.getTrendingMovies()
}