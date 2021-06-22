package com.narvi.delivery.movie.data.api

import com.narvi.delivery.movie.data.model.Review

interface ReviewApi {
    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllMovieReviews(movieId: String) : List<Review>

    suspend fun getAllUserReviews(userId: String) : List<Review>
}