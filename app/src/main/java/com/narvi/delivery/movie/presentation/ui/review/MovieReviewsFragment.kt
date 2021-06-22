package com.narvi.delivery.movie.presentation.ui.review

import android.app.Activity
import android.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.MovieReviews
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
        showMovieInformation(arguments.movie)
    }

    override fun observeData() = with(viewModel) {
        movieReviewState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MovieReviewState.Loading -> {
                    handleLoading()
                }
                is MovieReviewState.Success -> {
                    showReviews(it.reviews)
                }
                is MovieReviewState.Error -> {
                    handleError(it.message)
                }
                is MovieReviewState.ToastError -> {
                    handleToastError(it.message)
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

    private fun showMovieInformation(movie: Movie) {
        movieReviewAdapter.apply {
            onReviewSubmitButtonClickListener = { content, score ->
                viewModel.requestAddReview(movie, content, score)
                hideKeyboard()
            }

            onReviewDeleteButtonClickListener = { review ->
                showDeleteConfirmDialog(review)
            }
        }
    }

    private fun showDeleteConfirmDialog(review: Review) {
        AlertDialog.Builder(requireContext())
            .setMessage("정말로 리뷰를 삭제하시겠어요?")
            .setPositiveButton("삭제 할래요") { _, _ ->
                viewModel.requestRemoveReview(review)
            }
            .setNegativeButton("안할래요") { _, _, ->

            }
            .show()
    }

    private fun handleLoading() {
        binding.progressBar.toVisible()
    }

    private fun showReviews(reviews: MovieReviews) {
        binding.progressBar.toGone()
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()
        movieReviewAdapter.apply {
            this.reviews = reviews.othersReview
            this.myReview = reviews.myReview
            notifyDataSetChanged()
        }
    }

    private fun handleError(message: String) {
        binding.progressBar.toGone()
        binding.recyclerView.toGone()
        binding.errorDescriptionTextView.toVisible()
        binding.errorDescriptionTextView.text = message
    }

    private fun handleToastError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

}