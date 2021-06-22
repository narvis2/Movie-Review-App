package com.narvi.delivery.movie.data.repository.datasource

import com.narvi.delivery.movie.data.model.Review

interface ReviewApiDataSource {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllMovieReviews(movieId: String) : List<Review>

    suspend fun getAllUserReviews(userId: String) : List<Review>
}