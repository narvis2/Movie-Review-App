package com.narvi.delivery.movie.data.model

data class MovieReviews(
    val myReview: Review?,
    val othersReview: List<Review>
)
