package com.narvi.delivery.movie.domain.usecase

import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.domain.repository.MovieRepository

class GetAllMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() : List<Movie> {
        return movieRepository.getAllMovies()
    }
}