package games.bahasa.imdbmovie.adapter

import android.util.Log
import android.view.View

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.xwray.groupie.viewbinding.BindableItem
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.databinding.ItemMoviesBinding
import games.bahasa.imdbmovie.model.DataTvShow
import games.bahasa.imdbmovie.utils.Utility.setimageglide

class AdapterTelevision(val item: DataTvShow) : BindableItem<ItemMoviesBinding?>() {

    override fun initializeViewBinding(view: View): ItemMoviesBinding =
        ItemMoviesBinding.bind(view)

    override fun getLayout(): Int {
        return R.layout.item_movies
    }

    override fun bind(viewBinding: ItemMoviesBinding, position: Int) {
        viewBinding.apply {
            Log.d("item_backdrop",item.backdrop_path.toString())
            val url = "https://image.tmdb.org/t/p/w500"
            titleTv.text = item.name

            setimageglide(root.context,
                url+item.poster_path, posterImg)

            setimageglide(root.context,
                url+item.backdrop_path, backdropImg)

            if (item.adult){
                ageTv.text = "17+"
            }else{
                ageTv.text = "All Age"
            }
            langTv.text = "lang " +item.original_language
            dateTv.text = item.first_air_date

            buyTv.text =  "tv program"
        }
    }
}