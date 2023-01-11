package com.gdscug.mooflix.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityHomeBinding
import com.gdscug.mooflix.ui.detail.DetailActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvMovies
        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(
            this@HomeActivity,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
    }

    private fun setupData() {
        val movieAdapter = MovieAdapter()
        val movies = homeViewModel.getMovies()
        movieAdapter.submitList(movies)

        binding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter

        }

        movieAdapter.onItemClickCallback = object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MoviesEntity) {
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    startActivity(this)
                }
            }

        }
    }
}