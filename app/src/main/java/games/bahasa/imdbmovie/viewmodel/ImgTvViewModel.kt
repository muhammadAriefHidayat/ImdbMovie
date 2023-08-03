package games.bahasa.imdbmovie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import games.bahasa.imdbmovie.data.repository.MainRepository
import games.bahasa.imdbmovie.model.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImgTvViewModel(private val repository: MainRepository) : ViewModel() {


    private val mResponse = MutableLiveData<List<DataBackdrop>?>()
    internal fun getResponse(): MutableLiveData<List<DataBackdrop>?> =  mResponse

    fun getImagesTv(id:Int) = viewModelScope.launch {
        repository.getImagesTv(id).collect {
            it.enqueue(object : Callback<ImagesBackdrop> {
                override fun onResponse(
                    call: Call<ImagesBackdrop>,
                    response: Response<ImagesBackdrop>
                ) {
                    val datas = response.body()?.backdrops
                    Log.d("IMgTv",datas.toString())
                    Log.d("IMgTdv",response.body().toString())
                    Log.d("IMgTfv",response.toString())
                    Log.d("IMgTfv",response.isSuccessful.toString())
                    mResponse.value = datas
                }

                override fun onFailure(call: Call<ImagesBackdrop>, t: Throwable) {
                    Log.d("Tv", "error")
                    Log.d("Tv", t.message.toString())
                }

            })
        }
    }

}