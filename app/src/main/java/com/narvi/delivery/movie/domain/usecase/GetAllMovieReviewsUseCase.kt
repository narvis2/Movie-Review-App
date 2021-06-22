package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.MovieReviews
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.domain.repository.ReviewRepository
import com.narvi.delivery.movie.domain.repository.UserRepository

class GetAllMovieReviewsUseCase(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(movieId: String) : MovieReviews {
        val reviews = reviewRepository.getAllMovieReviews(movieId)
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            return MovieReviews(null, reviews)
        }

        val result = MovieReviews(
            myReview = reviews.find {
                it.userId == user.id
            },
            othersReview = reviews.filter {
                it.userId != user.id
            }
        )

        return result
    }
}