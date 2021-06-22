package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.mapper.toListString
import com.narvi.delivery.movie.data.model.ReviewedMovie
import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.domain.repository.MovieRepository
import com.narvi.delivery.movie.domain.repository.ReviewRepository
import com.narvi.delivery.movie.domain.repository.UserRepository

class GetMyReviewedMoviesUseCase(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke() : List<ReviewedMovie> {
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            return emptyList()
        }

        val reviews = reviewRepository.getAllUserReviews(user.id!!)
            .filter {
                it.movieId.isNullOrBlank().not()
            }

        if (reviews.isNullOrEmpty()) {
            return emptyList()
        }

        val result = movieRepository.getMovies(reviews.toListString())
            .mapNotNull { movie ->
                val relatedReview = reviews.find {
                    it.movieId == movie.id
                }
                relatedReview?.let { review->
                    ReviewedMovie(movie, review)
                }
            }

        return result
    }
}