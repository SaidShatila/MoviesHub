package com.app.movieshub.movie

import android.media.Rating
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.app.minimvvmproject.databinding.MovieDetailsBinding
import com.app.movieshub.DummyDependencyProvider
import com.app.movieshub.data.api.MovieRepository
//private lateinit var viewModel: MovieDetailsViewModel
//private lateinit var successLayout: View
//private lateinit var overview: TextView
//private lateinit var popularity: TextView
//private lateinit var posterPath: TextView
//private lateinit var releaseDate: TextView
//private lateinit var revenue: TextView
//private lateinit var runtime: TextView
//private lateinit var status: TextView
//private lateinit var tagline: TextView
//private lateinit var title: TextView
//private lateinit var video: TextView
//private lateinit var rating: RatingBar
//private lateinit var progressBar: ProgressBar

class MovieDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = MovieDetailsViewModel(movieRepository =DummyDependencyProvider.movieRepository,movieId = "581726")
        return binding.root
    }
//    private fun initView(){
//        successLayout
//    }
}
