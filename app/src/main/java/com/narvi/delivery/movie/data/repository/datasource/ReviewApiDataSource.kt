package com.narvi.delivery.movie.data.repository.datasource

import com.narvi.delivery.movie.data.model.Review

interface ReviewApiDataSource {

    suspend fun getLatestReview(movieId: String): Review?

}