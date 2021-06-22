package com.narvi.delivery.movie.data.repository.datasourceimpl

import com.narvi.delivery.movie.data.api.ReviewApi
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.data.repository.datasource.ReviewApiDataSource

class ReviewApiDataSourceImpl(
    private val reviewApi: ReviewApi
) : ReviewApiDataSource {
    override suspend fun getLatestReview(movieId: String): Review? {
        return reviewApi.getLatestReview(movieId)
    }

    override suspend fun getAllMovieReviews(movieId: String): List<Review> {
        return reviewApi.getAllMovieReviews(movieId)
    }

    override suspend fun getAllUserReviews(userId: String): List<Review> {
        return reviewApi.getAllUserReviews(userId)
    }

    override suspend fun addReview(review: Review): Review {
        return reviewApi.addReview(review)
    }

    override suspend fun removeReview(review: Review) {
        return reviewApi.removeReview(review)
    }
}