package games.bahasa.imdbmovie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import games.bahasa.imdbmovie.data.repository.MainRepository
import games.bahasa.imdbmovie.model.DataTvShow
import games.bahasa.imdbmovie.model.Tv
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchTvViewModel(private val repository: MainRepository) : ViewModel() {


    private val mResponse = MutableLiveData<List<DataTvShow>?>()
    internal fun getResponse(): MutableLiveData<List<DataTvShow>?> =  mResponse

    fun getSearchTv(query:String) = viewModelScope.launch {
        repository.getSearchTv(query).collect {
            it.enqueue(object : Callback<Tv> {
                override fun onResponse(
                    call: Call<Tv>,
                    response: Response<Tv>
                ) {
                    val datas = response.body()?.results
                    Log.d("Tv",datas.toString())
                    mResponse.value = datas
                }

                override fun onFailure(call: Call<Tv>, t: Throwable) {
                    Log.d("Tv", "error")
                    Log.d("Tv", t.message.toString())
                }

            })
        }
    }

}