package games.bahasa.imdbmovie.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.denzcoskun.imageslider.models.SlideModel
import games.bahasa.imdbmovie.adapter.AdapterTelevision
import games.bahasa.imdbmovie.databinding.ActivityDetailTvBinding
import games.bahasa.imdbmovie.model.DataTvShow
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.ImgTvViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory

class DetailTvActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailTvBinding
    private val imgTvShow: ImgTvViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    val url = "https://image.tmdb.org/t/p/w500/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<DataTvShow>("tv")
        val imageList = ArrayList<SlideModel>()

        if (detailMovie != null) {
            imgTvShow.getImagesTv(detailMovie.id)
            Log.d("bakdroud", detailMovie.id.toString())
            imgTvShow.getResponse().observeOnce(this) {
                it?.subList(0, 6)?.forEach { draw ->
                    Log.d("bakdroud", draw.filePath.toString())
                    imageList.add(SlideModel(url+draw.filePath))
                    imageList.forEach {
                        Log.d("databkdr", it.toString())
                    }
                    binding.backdropImg.setImageList(imageList)
                }
            }
        }

        binding.apply {
            titleTv.text = detailMovie?.name
            Glide.with(this@DetailTvActivity)
                .load("https://image.tmdb.org/t/p/w500" + detailMovie?.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterImg)


            rateTv.text = "rate: ${detailMovie?.vote_average}"
            langTv.text = detailMovie?.original_language
            overviewTv.text = detailMovie?.overview
            dateTv.text = detailMovie?.first_air_date
        }
    }

    fun setSlider(){

    }
}