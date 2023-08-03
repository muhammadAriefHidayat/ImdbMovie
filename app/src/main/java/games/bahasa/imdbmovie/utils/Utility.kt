package games.bahasa.imdbmovie.utils

import android.content.Context
import android.widget.Toast

object Utility {

    fun checkInternet(context: Context){
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if (internet == true) {

                }else{
                    Toast.makeText(context, "tidak ada internet", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}