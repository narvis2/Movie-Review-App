package com.narvi.delivery.movie.presentation.ui.review

import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review

sealed class MovieReviewState {

    object Loading : MovieReviewState()

    data class Success(
        val reviewList : List<Review>
    ) : MovieReviewState()

    data class Error(
        val message: String
    ) : MovieReviewState()

}