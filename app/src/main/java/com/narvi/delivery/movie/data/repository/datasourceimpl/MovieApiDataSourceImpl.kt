package com.narvi.delivery.movie.data.repository.datasourceimpl

import com.narvi.delivery.movie.data.api.MovieApi
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.repository.datasource.MovieApiDataSource

class MovieApiDataSourceImpl(
    private val movieApi: MovieApi
) : MovieApiDataSource {

    override suspend fun getAllMovies(): List<Movie> {
        return movieApi.getAllMovies()
    }
}