package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.domain.repository.ReviewRepository

class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(review: Review) {
        reviewRepository.removeReview(review)
    }
}