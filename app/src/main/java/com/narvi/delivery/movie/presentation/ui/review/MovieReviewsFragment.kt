package com.narvi.delivery.movie.presentation.ui.review

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.databinding.FragmentMovieReviewsBinding
import com.narvi.delivery.movie.presentation.base.BaseFragment
import com.narvi.delivery.movie.presentation.extension.toGone
import com.narvi.delivery.movie.presentation.extension.toVisible
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieReviewsFragment : BaseFragment<FragmentMovieReviewsBinding, MovieReviewsViewModel>(
    R.layout.fragment_movie_reviews
) {
    override val viewModel: MovieReviewsViewModel by viewModel()

    private lateinit var movieReviewAdapter : MovieReviewsAdapter

    private val arguments : MovieReviewsFragmentArgs by navArgs()

    override fun onCreate() {
        movieReviewAdapter = MovieReviewsAdapter(arguments.movie)
        initViews()
        viewModel.fetchReviews(arguments.movie)
    }

    override fun observeData() = with(viewModel) {
        movieReviewState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MovieReviewState.Loading -> {
                    handleLoading()
                }
                is MovieReviewState.Success -> {
                    showReviews(it.reviewList)
                }
                is MovieReviewState.Error -> {
                    handleError(it.message)
                }
            }
        })
    }

    private fun initViews() {
        binding.recyclerView.apply {
            adapter = movieReviewAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun handleLoading() {
        binding.progressBar.toVisible()
    }

    private fun showReviews(reviews: List<Review>) {
        binding.progressBar.toGone()
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()
        movieReviewAdapter.apply {
            this.reviews = reviews
            notifyDataSetChanged()
        }
    }

    private fun handleError(message: String) {
        binding.progressBar.toGone()
        binding.recyclerView.toGone()
        binding.errorDescriptionTextView.toVisible()
        binding.errorDescriptionTextView.text = message
    }

}