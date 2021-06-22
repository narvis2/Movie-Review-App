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
}