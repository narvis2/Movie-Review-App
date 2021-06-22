package com.narvi.delivery.movie.presentation.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.MovieReviews
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.domain.usecase.DeleteReviewUseCase
import com.narvi.delivery.movie.domain.usecase.GetAllMovieReviewsUseCase
import com.narvi.delivery.movie.domain.usecase.SubmitReviewUseCase
import com.narvi.delivery.movie.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.Exception

class MovieReviewsViewModel(
    private val getAllMovieReviewsUseCase: GetAllMovieReviewsUseCase,
    private val submitReviewUseCase: SubmitReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase
) : BaseViewModel() {

    private val _movieReviewState = MutableLiveData<MovieReviewState>()
    val movieReviewState : LiveData<MovieReviewState>
        get() = _movieReviewState

    private var movieReviews: MovieReviews = MovieReviews(null, emptyList())

    fun requestAddReview(movie: Movie,content: String, score: Float) = viewModelScope.launch {
        try {
            _movieReviewState.postValue(MovieReviewState.Loading)
            val submittedReview = submitReviewUseCase(movie, content, score)
            _movieReviewState.postValue(MovieReviewState.Success(movieReviews.copy(myReview = submittedReview)))
        }catch (e:Exception) {
            e.printStackTrace()
            _movieReviewState.postValue(MovieReviewState.ToastError("리뷰 등록을 실패했어요 ㅠㅠ"))
        }
    }

    fun requestRemoveReview(review: Review) = viewModelScope.launch {
        try {
            _movieReviewState.postValue(MovieReviewState.Loading)
            deleteReviewUseCase(review)
            _movieReviewState.postValue(MovieReviewState.Success(movieReviews))
        }catch (e:Exception) {
            e.printStackTrace()
            _movieReviewState.postValue(MovieReviewState.Error("알 수 없는 오류가 발생하였습니다."))
        }
    }

    fun fetchReviews(movie: Movie) = viewModelScope.launch {
        try {
            _movieReviewState.postValue(MovieReviewState.Loading)
            val response = getAllMovieReviewsUseCase(movieId = movie.id!!)
            movieReviews = response
            _movieReviewState.postValue(MovieReviewState.Success(movieReviews))
        }catch (e: Exception) {
            e.printStackTrace()
            _movieReviewState.postValue(MovieReviewState.Error("에러가 발생했어요 ㅠㅠ"))
        }
    }
}