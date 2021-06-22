package com.narvi.delivery.movie.domain.repository

import com.narvi.delivery.movie.data.model.Review

interface ReviewRepository {

    suspend fun getLatestReview(movieId: String): Review?
}