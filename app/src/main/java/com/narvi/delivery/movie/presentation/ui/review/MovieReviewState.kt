package com.narvi.delivery.movie.presentation.ui.review

import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.MovieReviews
import com.narvi.delivery.movie.data.model.Review

sealed class MovieReviewState {

    object Loading : MovieReviewState()

    data class Success(
        val reviews : MovieReviews
    ) : MovieReviewState()

    data class Error(
        val message: String
    ) : MovieReviewState()

    data class ToastError(
        val message: String
    ) : MovieReviewState()

}