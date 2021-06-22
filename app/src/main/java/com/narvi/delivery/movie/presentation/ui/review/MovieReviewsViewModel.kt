package com.narvi.delivery.movie.presentation.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.domain.usecase.GetAllMovieReviewsUseCase
import com.narvi.delivery.movie.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieReviewsViewModel(
    private val getAllMovieReviewsUseCase: GetAllMovieReviewsUseCase
) : BaseViewModel() {

    private val _movieReviewState = MutableLiveData<MovieReviewState>()
    val movieReviewState : LiveData<MovieReviewState>
        get() = _movieReviewState

    fun fetchReviews(movie: Movie) = viewModelScope.launch {
        try {
            _movieReviewState.postValue(MovieReviewState.Loading)
            val response = getAllMovieReviewsUseCase(movieId = movie.id!!)
            _movieReviewState.postValue(MovieReviewState.Success(response))
        }catch (e: Exception) {
            e.printStackTrace()
            _movieReviewState.postValue(MovieReviewState.Error("에러가 발생했어요 ㅠㅠ"))
        }
    }
}