package com.narvi.delivery.movie.data.api

import com.narvi.delivery.movie.data.model.Movie

interface MovieApi {

    suspend fun getAllMovies() : List<Movie>

    suspend fun getMovies(movieIds: List<String>) : List<Movie>
}