package com.app.movieshub.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.minimvvmproject.databinding.ItemMovieBinding
import com.app.movieshub.data.entities.CompactMovie
import com.app.movieshub.data.entities.Movie
import com.app.movieshub.movies.CompactMoviesViewModel

class MoviesListAdapter(
    private val moviesList: List<CompactMovie>,
    private val onMovieClicked: (CompactMovie) -> Unit
) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.viewModel = CompactMoviesViewModel(moviesList[position], onMovieClicked)
    }

    override fun getItemCount() = moviesList.size
}

class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)