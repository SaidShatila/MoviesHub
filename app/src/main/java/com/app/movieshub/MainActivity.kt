package com.app.movieshub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.app.minimvvmproject.R
import com.app.movieshub.movies.MoviesListFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainer,
                MoviesListFragment())
        }
    }
}
