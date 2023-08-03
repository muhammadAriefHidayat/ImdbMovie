package games.bahasa.imdbmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.databinding.ActivityDetailBinding
import games.bahasa.imdbmovie.databinding.FragmentMovieBinding
import games.bahasa.imdbmovie.model.DataMovie
import games.bahasa.imdbmovie.model.Movies

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<DataMovie>("dmovies")
        binding.apply {
            titleTv.text = detailMovie?.title

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detailMovie?.backdrop_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backdropImg)

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detailMovie?.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterImg)

//            if (item.adult) {
//                ageTv.text = "17+"
//            } else {
//                ageTv.text = "SU"
//            }
//            langTv.text = item.original_language
//            dateTv.text = item.release_date
        }
    }
}