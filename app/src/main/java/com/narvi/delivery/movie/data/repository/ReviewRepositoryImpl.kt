package com.narvi.delivery.movie.data.repository

import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.data.repository.datasource.ReviewApiDataSource
import com.narvi.delivery.movie.domain.repository.ReviewRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ReviewRepositoryImpl(
    private val reviewApiDataSource: ReviewApiDataSource,
    private val dispatcher: CoroutineDispatcher
) : ReviewRepository {

    override suspend fun getLatestReview(movieId: String): Review? = withContext(dispatcher) {
        reviewApiDataSource.getLatestReview(movieId)
    }

    override suspend fun getAllMovieReviews(movieId: String): List<Review> = withContext(dispatcher) {
        reviewApiDataSource.getAllMovieReviews(movieId)
    }

    override suspend fun getAllUserReviews(userId: String): List<Review> = withContext(dispatcher) {
        reviewApiDataSource.getAllUserReviews(userId)
    }

    override suspend fun addReview(review: Review): Review = withContext(dispatcher){
        reviewApiDataSource.addReview(review)
    }

    override suspend fun removeReview(review: Review) = withContext(dispatcher) {
        reviewApiDataSource.removeReview(review)
    }
}