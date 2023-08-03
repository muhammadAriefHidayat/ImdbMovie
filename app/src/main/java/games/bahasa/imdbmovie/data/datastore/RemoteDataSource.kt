package games.bahasa.imdbmovie.data.datastore

import android.util.Log

import games.bahasa.imdbmovie.data.api.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiServices: ApiServices) {

    fun getTrendingMovies() = flow {
        emit(apiServices.getTrendingMovies())
    }.catch {
        Log.d("getTrendingMovies", "movies: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getTrendingTv() = flow {
        emit(apiServices.getTrendingTv())
    }.catch {
        Log.d("getTrendingTv", "tv: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}
