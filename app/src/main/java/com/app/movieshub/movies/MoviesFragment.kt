package com.app.movieshub.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.minimvvmproject.databinding.MovieDetailsBinding
import com.app.movieshub.DummyDependencyProvider
import com.app.movieshub.movie.MovieDetailsViewModel

class MoviesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = MovieDetailsViewModel(
            movieRepository = DummyDependencyProvider.movieRepository,
            movieId = "581726"
        )
        return binding.root
    }
}