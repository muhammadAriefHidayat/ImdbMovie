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

            val formattedRating = detailMovie?.vote_average?.toFloat()
            if (formattedRating != null) {
                ratingBar.rating = formattedRating
            }

            rateTv.text = "rate: ${detailMovie?.vote_average}"
            langTv.text = detailMovie?.original_language
            overviewTv.text = detailMovie?.overview
            dateTv.text = detailMovie?.release_date
        }
    }
}