package games.bahasa.imdbmovie.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.utils.InternetCheck
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.TelevisionViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory


class TvFragment : Fragment() {

    private val televisionViewModel: TelevisionViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        televisionViewModel.getTrendingTv()
        checkInternet()
        televisionViewModel.getResponse().observeOnce(requireActivity()){itemArrayList->
            itemArrayList?.forEach {
                Log.d("member", it.name)
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
        fun newHomeInstance(position: Int): TvFragment {
            val fragment = TvFragment()
            val args = Bundle()
            args.putInt("POSITION", position)
            fragment.arguments = args
            return fragment
        }
    }
}