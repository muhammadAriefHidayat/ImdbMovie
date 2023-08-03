package games.bahasa.imdbmovie.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import games.bahasa.imdbmovie.data.repository.MainRepository
import games.bahasa.imdbmovie.di.Injection


class ViewModelFactory (private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            return MoviesViewModel(mainRepository) as T
        }
        else if (modelClass.isAssignableFrom(TelevisionViewModel::class.java)){
            return TelevisionViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also {
                instance = it
            }
    }
}