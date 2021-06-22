package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.FeaturedMovie
import com.narvi.delivery.movie.domain.repository.MovieRepository
import com.narvi.delivery.movie.domain.repository.ReviewRepository

class GetRandomFeaturedMovieUseCase(
    private val movieRepository: MovieRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke() : FeaturedMovie? {
        val featuredMovie = movieRepository.getAllMovies()
            .filter {
                it.id.isNullOrBlank().not()
            }
            .filter {
                it.isFeatured == true
            }

        if (featuredMovie.isEmpty()) {
            return null
        }

        val result = featuredMovie.random().let {
            val latestReview  = reviewRepository.getLatestReview(it.id!!)
            FeaturedMovie(it, latestReview)
        }

        return result
    }
}