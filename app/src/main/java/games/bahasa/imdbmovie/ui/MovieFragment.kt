package games.bahasa.imdbmovie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.utils.InternetCheck
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.MoviesViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory


class MovieFragment : Fragment() {


    private val tMoviesViewModel: MoviesViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tMoviesViewModel.getTrendingMovies()
        checkInternet()
        tMoviesViewModel.getResponse().observeOnce(requireActivity()){itemArrayList->
            itemArrayList?.forEach {
                Log.d("member", it.title)
//                groupAdapter.add(AdapterMember(it))
            }
        }
    }
    fun checkInternet(){
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if (internet == true) {

                }
            }
        })
    }

    companion object {
        fun newHomeInstance(position: Int): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putInt("POSITION", position)
            fragment.arguments = args
            return fragment
        }
    }
}