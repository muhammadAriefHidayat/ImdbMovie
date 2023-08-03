package games.bahasa.imdbmovie.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object Utility {

    fun setImageToImageView(context: Context, imageView: ImageView, drawable: Int) {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
            .skipMemoryCache(true)

        Glide.with(context)
            .load(drawable)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

    fun checkInternet(context: Context) {
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if (internet == true) {

                } else {
                    Toast.makeText(context, "tidak ada internet", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}