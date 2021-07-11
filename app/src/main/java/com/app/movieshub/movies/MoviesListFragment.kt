package com.app.movieshub.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.app.minimvvmproject.R
import com.app.minimvvmproject.databinding.MoviesListBinding
import com.app.movieshub.DummyDependencyProvider
import com.app.movieshub.movie.MovieDetailsFragment

class MoviesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesListBinding.inflate(inflater, container, false)
        binding.viewModel = MoviesListViewModel(
            movieRepository = DummyDependencyProvider.movieRepository,
            page = 1,
            onMovieClicked = { movie ->


                requireActivity().supportFragmentManager.commit {
                    addToBackStack("MovieDetailsFragment")
                    replace(
                        R.id.fragmentContainer,
                        MovieDetailsFragment.createInstance(movie.id.toString())
                    )
                }
            }
        )
        return binding.root
    }
}