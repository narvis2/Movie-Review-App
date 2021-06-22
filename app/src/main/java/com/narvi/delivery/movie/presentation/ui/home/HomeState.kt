package com.narvi.delivery.movie.presentation.ui.home

import com.narvi.delivery.movie.data.model.FeaturedMovie
import com.narvi.delivery.movie.data.model.Movie

sealed class HomeState {
    object UnInitialized : HomeState()

    object Loading : HomeState()

    data class Success(
        val featuredMovie : FeaturedMovie?,
        val movieList : List<Movie>
    ) : HomeState()

    data class Error(
        val message: String?
    ) : HomeState()

}
