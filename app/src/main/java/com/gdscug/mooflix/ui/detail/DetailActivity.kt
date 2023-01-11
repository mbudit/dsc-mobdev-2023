package com.gdscug.mooflix.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var data: MoviesEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        val movies = intent.extras
        if (movies != null) {
            data = intent.getParcelableExtra(EXTRA_MOVIE)
            data?.let {
                setupData(it)
            }
        }
    }

    private fun setupView(){
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupData(moviesEntity: MoviesEntity) {
        binding.apply {
            tvTitleDetail.text = moviesEntity.title
            tvReleaseDate.text = moviesEntity.releaseDate
            tvVoteAverage.text = moviesEntity.voteAverage
            tvOverview.text = moviesEntity.overview
            Glide.with(this@DetailActivity)
                .load(moviesEntity.posterPath)
                .into(ivPosterDetail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}