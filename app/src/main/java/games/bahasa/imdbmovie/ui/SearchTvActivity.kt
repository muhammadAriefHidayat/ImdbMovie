package games.bahasa.imdbmovie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import com.xwray.groupie.GroupieAdapter
import games.bahasa.imdbmovie.adapter.AdapterMovies
import games.bahasa.imdbmovie.adapter.AdapterTelevision
import games.bahasa.imdbmovie.databinding.ActivitySearchTvBinding
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.SearchMovieViewModel
import games.bahasa.imdbmovie.viewmodel.SearchTvViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory

class SearchTvActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchTvBinding
    private lateinit var groupAdapter: GroupieAdapter

    private val searchTvViewModel: SearchTvViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupAdapter = GroupieAdapter()

        binding.queryEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getTv(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        groupAdapter.setOnItemClickListener { item, view ->
            val intent = Intent(this, DetailTvActivity::class.java)
            val itemdata = item as AdapterTelevision
            intent.putExtra("tv",itemdata.item)
            startActivity(intent)
        }
    }


    fun getTv(s:String) {
        groupAdapter.clear()
        groupAdapter.notifyDataSetChanged()
        searchTvViewModel.getSearchTv(s)
        searchTvViewModel.getResponse().observeOnce(this) {
            it?.forEach { draw ->
                Log.d("serch", draw.toString())
                groupAdapter.add(AdapterTelevision(draw))
            }
        }
        binding.recycleviewMovies.adapter = groupAdapter
    }
}