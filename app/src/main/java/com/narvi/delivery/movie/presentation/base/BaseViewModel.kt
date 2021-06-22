package com.narvi.delivery.movie.presentation.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    // xml 에서 android:visibility="@{vm.isLoading() ? View.VISIBLE : View.GONE}" 이렇게 사용 <import type="android.view.View" /> 추가
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    // viewModel 에서 사용
    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }
}