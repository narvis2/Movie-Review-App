package com.narvi.delivery.movie.data.repository

import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.repository.datasource.MovieApiDataSource
import com.narvi.delivery.movie.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieApiDataSource: MovieApiDataSource,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getAllMovies(): List<Movie> = withContext(dispatcher) {
        movieApiDataSource.getAllMovies()
    }

    override suspend fun getMovies(movieIds: List<String>): List<Movie> = withContext(dispatcher) {
        movieApiDataSource.getMovies(movieIds)
    }
}