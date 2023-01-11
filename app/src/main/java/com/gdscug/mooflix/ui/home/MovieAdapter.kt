package com.gdscug.mooflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<MoviesEntity, MovieAdapter.MovieViewHolder>(DIFFUTILS) {
    private object DIFFUTILS : DiffUtil.ItemCallback<MoviesEntity>() {
        override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem == newItem
        }
    }

    var onItemClickCallback: OnItemClickCallback? = null

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity, itemClicked: () -> Unit) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .into(ivPoster)
            }
            itemView.setOnClickListener { itemClicked.invoke() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie) {
            onItemClickCallback?.onItemClicked(movie)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: MoviesEntity)
    }

}