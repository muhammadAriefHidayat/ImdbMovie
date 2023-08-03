package games.bahasa.imdbmovie.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xwray.groupie.GroupieAdapter
import games.bahasa.imdbmovie.adapter.AdapterMovies
import games.bahasa.imdbmovie.databinding.ActivitySearchMovieBinding
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.SearchMovieViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory

class SearchMovieActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchMovieBinding
    private lateinit var groupAdapter: GroupieAdapter

    private val searchMoviesViewModel: SearchMovieViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        groupAdapter = GroupieAdapter()

        binding.queryEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getmovies(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        val intent = Intent(this, DetailActivity::class.java)
        groupAdapter.setOnItemClickListener { item, view ->
            val itemdata = item as AdapterMovies
            intent.putExtra("dmovies",itemdata.item)
            startActivity(intent)
        }

    }

    fun getmovies(s:String) {
        groupAdapter.clear()
        groupAdapter.notifyDataSetChanged()
        searchMoviesViewModel.getSearchMovie(s)
        searchMoviesViewModel.getResponse().observeOnce(this@SearchMovieActivity) {
            it?.forEach { draw ->
                Log.d("serch", draw.toString())
                groupAdapter.add(AdapterMovies(draw))
            }
        }
        binding.recycleviewMovies.adapter = groupAdapter
    }
}