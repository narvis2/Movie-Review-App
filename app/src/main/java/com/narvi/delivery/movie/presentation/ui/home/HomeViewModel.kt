package com.narvi.delivery.movie.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.narvi.delivery.movie.domain.usecase.GetAllMoviesUseCase
import com.narvi.delivery.movie.domain.usecase.GetRandomFeaturedMovieUseCase
import com.narvi.delivery.movie.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getRandomFeaturedMovieUseCase: GetRandomFeaturedMovieUseCase
) : BaseViewModel() {

    private val _homeState = MutableLiveData<HomeState>(HomeState.UnInitialized)
    val homeState : LiveData<HomeState>
        get() = _homeState

    fun fetchMovie() = viewModelScope.launch {
        try {
            _homeState.postValue(HomeState.Loading)
            val featuredMovie = getRandomFeaturedMovieUseCase()
            val movieList = getAllMoviesUseCase()
            _homeState.postValue(
                HomeState.Success(
                    featuredMovie = featuredMovie,
                    movieList = movieList
                )
            )
        } catch (e:Exception) {
            e.printStackTrace()
            _homeState.postValue(HomeState.Error(e.message))
        }
    }

}