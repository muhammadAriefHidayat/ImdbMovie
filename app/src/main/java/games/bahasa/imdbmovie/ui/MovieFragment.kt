package games.bahasa.imdbmovie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import games.bahasa.imdbmovie.adapter.AdapterMovies
import games.bahasa.imdbmovie.databinding.FragmentMovieBinding
import games.bahasa.imdbmovie.utils.InternetCheck
import games.bahasa.imdbmovie.utils.Utility.checkInternet
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.MoviesViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory
import kotlin.math.log


class MovieFragment : Fragment() {

    private lateinit var groupAdapter: GroupieAdapter
    private lateinit var binding:FragmentMovieBinding

    private val tMoviesViewModel: MoviesViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupAdapter = GroupieAdapter()
        tMoviesViewModel.getTrendingMovies()
        chekInternet()

        val intent = Intent(requireActivity(), DetailActivity::class.java)
        groupAdapter.setOnItemClickListener { item, view ->
            val itemdata = item as AdapterMovies
            intent.putExtra("dmovies",itemdata.item)
            startActivity(intent)
        }

        binding.recycleviewMovies.adapter = groupAdapter
    }

    private fun chekInternet() {
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if (internet == true) {
                    binding.appCompatButton.visibility = View.INVISIBLE
                    tMoviesViewModel.getResponse().observeOnce(requireActivity()) {
                        it?.forEach { draw ->
                            groupAdapter.add(AdapterMovies(draw))
                        }
                    }
                    if (tMoviesViewModel.getResponse().hasObservers()) {
                        binding.progressbar.visibility = View.INVISIBLE
                    }
                } else {
                    Toast.makeText(requireContext(), "turn your internet", Toast.LENGTH_SHORT).show()
                    binding.appCompatButton.visibility = View.VISIBLE
                    binding.appCompatButton.setOnClickListener {
                        chekInternet()
                    }
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