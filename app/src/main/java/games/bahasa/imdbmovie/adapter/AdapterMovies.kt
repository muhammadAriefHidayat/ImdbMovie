package games.bahasa.imdbmovie.adapter

import android.util.Log
import android.view.View

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.xwray.groupie.viewbinding.BindableItem
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.databinding.ItemMoviesBinding
import games.bahasa.imdbmovie.model.DataMovie

class AdapterMovies(val item: DataMovie) : BindableItem<ItemMoviesBinding?>() {

    override fun initializeViewBinding(view: View): ItemMoviesBinding =
        ItemMoviesBinding.bind(view)

    override fun getLayout(): Int {
        return R.layout.item_movies
    }

    override fun bind(viewBinding: ItemMoviesBinding, position: Int) {
        viewBinding.apply {
            Log.d("item_backdrop",item.backdrop_path.toString())

            titleTv.text = item.title

            Glide.with(viewBinding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+item.backdrop_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backdropImg)

            Glide.with(viewBinding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterImg)
            if (item.adult){
                ageTv.text = "17+"
            }else{
                ageTv.text = "SU"
            }
            langTv.text = item.original_language
            dateTv.text = item.release_date

        }
    }
}