package com.narvi.delivery.movie.domain.repository

import com.narvi.delivery.movie.data.model.Movie

interface MovieRepository {

    suspend fun getAllMovies() : List<Movie>
}