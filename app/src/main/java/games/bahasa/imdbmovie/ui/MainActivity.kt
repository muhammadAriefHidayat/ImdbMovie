package games.bahasa.imdbmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import games.bahasa.imdbmovie.R
import games.bahasa.imdbmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val position =  1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment(MovieFragment.newHomeInstance(position))

        binding.apply {
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                if (item.itemId == android.R.id.home) {
                    finish()
                } else {
                    when (item.itemId) {
                        R.id.movie_menu -> {
                            setupFragment(MovieFragment.newHomeInstance(position))
                        }
                        R.id.tv_menu -> {
                            setupFragment(TvFragment.newHomeInstance(position))
                        }
                    }
                }

                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupFragment(fragment: Fragment) {
        try {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commitAllowingStateLoss()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}