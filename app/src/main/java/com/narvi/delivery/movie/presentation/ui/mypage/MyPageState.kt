package com.narvi.delivery.movie.presentation.ui.mypage

import com.narvi.delivery.movie.data.model.ReviewedMovie

sealed class MyPageState {
    object Loading : MyPageState()

    sealed class Success() : MyPageState() {
        data class NoDataDescription(
            val message: String
        ) : Success()

        data class ShowReviewedMovies(
            val reviewedMovies: List<ReviewedMovie>
        ) : Success()
    }

    data class Error(
        val message: String
    ) : MyPageState()
}
