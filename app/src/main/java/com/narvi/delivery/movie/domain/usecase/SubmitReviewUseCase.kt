package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.domain.repository.ReviewRepository
import com.narvi.delivery.movie.domain.repository.UserRepository

class SubmitReviewUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movie: Movie,
        content: String,
        score: Float
    )  : Review {
        var user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            user = userRepository.getUser()
        }

        val result = reviewRepository.addReview(
            Review(
                userId = user!!.id,
                movieId = movie.id,
                content = content,
                score = score
            )
        )

        return result
    }
}