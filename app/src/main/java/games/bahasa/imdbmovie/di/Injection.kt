package games.bahasa.imdbmovie.di

import android.content.Context
import games.bahasa.imdbmovie.data.api.ApiConfig
import games.bahasa.imdbmovie.data.datastore.RemoteDataSource
import games.bahasa.imdbmovie.data.repository.MainRepository


object Injection {
    fun provideRepository(context: Context): MainRepository {
        val apiServices = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource(apiServices)
        return MainRepository(remoteDataSource)
    }
}