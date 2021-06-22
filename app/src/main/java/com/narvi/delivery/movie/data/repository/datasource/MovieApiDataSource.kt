package com.narvi.delivery.movie.data.repository.datasource

import com.narvi.delivery.movie.data.model.Movie

interface MovieApiDataSource {

    suspend fun getAllMovies() : List<Movie>

    suspend fun getMovies(movieIds: List<String>) : List<Movie>
}