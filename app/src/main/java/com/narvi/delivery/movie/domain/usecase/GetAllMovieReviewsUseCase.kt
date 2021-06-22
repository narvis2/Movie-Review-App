package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.domain.repository.ReviewRepository

class GetAllMovieReviewsUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(movieId: String) : List<Review> {
        return reviewRepository.getAllMovieReviews(movieId)
    }
}