package com.narvi.delivery.movie.presentation.ui.review

import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.databinding.FragmentMovieReviewsBinding
import com.narvi.delivery.movie.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieReviewsFragment : BaseFragment<FragmentMovieReviewsBinding, MovieReviewsViewModel>(
    R.layout.fragment_movie_reviews
) {
    override val viewModel: MovieReviewsViewModel by viewModel()

    override fun onCreate() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }
}