package com.narvi.delivery.movie.presentation.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.narvi.delivery.movie.domain.usecase.GetMyReviewedMoviesUseCase
import com.narvi.delivery.movie.presentation.base.BaseViewModel
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val getMyReviewedMoviesUseCase: GetMyReviewedMoviesUseCase
) : BaseViewModel() {

    private val _myPageSate = MutableLiveData<MyPageState>()
    val myPageState : LiveData<MyPageState>
        get() = _myPageSate

    fun fetchReviewedMovies() = viewModelScope.launch {
        try {
            _myPageSate.postValue(MyPageState.Loading)
            val reviewedMovies = getMyReviewedMoviesUseCase()
            if (reviewedMovies.isNullOrEmpty()) {
                _myPageSate.postValue(
                    MyPageState.Success.NoDataDescription("아직 리뷰한 영화가 없어요.\n홈 탭을 눌러 영화를 리뷰해보세요 🙌")
                )
            } else {
                _myPageSate.postValue(MyPageState.Success.ShowReviewedMovies(reviewedMovies))
            }
        }catch (e: Exception) {
            e.printStackTrace()
            _myPageSate.postValue(MyPageState.Error("알 수 없는 에러가 발생하였습니다."))
        }
    }
}