package com.app.movieshub.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.minimvvmproject.databinding.MovieDetailsBinding
import com.app.movieshub.DummyDependencyProvider

private const val KEY_MOVIE_ID = "movieId"

class MovieDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieId: String =
            arguments?.getString(KEY_MOVIE_ID) ?: throw IllegalArgumentException("Movie id is missing")
        val binding = MovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = MovieDetailsViewModel(
            movieRepository = DummyDependencyProvider.movieRepository,
            movieId = movieId
        )
        return binding.root
    }

    companion object {
        fun createInstance(movieId: String): MovieDetailsFragment {
            val bundle = Bundle()
            bundle.putString(KEY_MOVIE_ID, movieId)

            val movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = bundle

            return movieDetailsFragment
        }
    }
}
