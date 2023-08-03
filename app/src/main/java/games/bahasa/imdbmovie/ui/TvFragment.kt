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
import com.xwray.groupie.GroupieAdapter
import games.bahasa.imdbmovie.adapter.AdapterMovies
import games.bahasa.imdbmovie.adapter.AdapterTelevision
import games.bahasa.imdbmovie.databinding.FragmentTvBinding
import games.bahasa.imdbmovie.utils.InternetCheck
import games.bahasa.imdbmovie.utils.observeOnce
import games.bahasa.imdbmovie.viewmodel.TelevisionViewModel
import games.bahasa.imdbmovie.viewmodel.ViewModelFactory


class TvFragment : Fragment() {

    private val televisionViewModel: TelevisionViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }
    private lateinit var groupAdapter: GroupieAdapter

    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded){
            groupAdapter = GroupieAdapter()
            televisionViewModel.getTrendingTv()

            binding.imgSearch.setOnClickListener {
                startActivity(Intent(requireContext(),SearchTvActivity::class.java))
            }
            getDataTV()
            setrecycleview()
        }
    }

    private fun setrecycleview() {
        if (isAdded){
            val intent = Intent(requireActivity(), DetailTvActivity::class.java)
            groupAdapter.setOnItemClickListener { item, view ->
                val itemdata = item as AdapterTelevision
                intent.putExtra("tv",itemdata.item)
                startActivity(intent)
            }
            binding.recycleviewTv.adapter = groupAdapter
        }
    }

    private fun getDataTV() {
        if (isAdded){
            televisionViewModel.getResponse().observeOnce(viewLifecycleOwner) {
                it?.forEach { draw ->
                    groupAdapter.add(AdapterTelevision(draw))
                }
            }
            if (televisionViewModel.getResponse().hasObservers()) {
                binding.progressbar.visibility = View.INVISIBLE
            }
        }
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