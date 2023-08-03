package games.bahasa.imdbmovie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import games.bahasa.imdbmovie.data.repository.MainRepository
import games.bahasa.imdbmovie.model.DataMovie
import games.bahasa.imdbmovie.model.Movies
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel(private val repository: MainRepository) : ViewModel() {


    private val mResponse = MutableLiveData<List<DataMovie>?>()
    internal fun getResponse(): MutableLiveData<List<DataMovie>?> =  mResponse

    fun getTrendingMovies() = viewModelScope.launch {
        repository.getTrendingMovies().collect {
            it.enqueue(object : Callback<Movies> {
                override fun onResponse(
                    call: Call<Movies>,
                    response: Response<Movies>
                ) {
                    val datas = response.body()?.results
                    Log.d("Movies",datas.toString())
                    mResponse.value = datas
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("Movies", "error")
                    Log.d("Movies", t.message.toString())
                }

            })
        }
    }

}