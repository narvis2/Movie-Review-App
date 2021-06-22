package com.narvi.delivery.movie.data.api

import com.narvi.delivery.movie.data.model.Review

interface ReviewApi {
    suspend fun getLatestReview(movieId: String): Review?
}