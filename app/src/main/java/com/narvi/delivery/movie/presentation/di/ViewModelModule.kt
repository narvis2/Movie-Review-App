package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.presentation.ui.home.HomeViewModel
import com.narvi.delivery.movie.presentation.ui.mypage.MyPageViewModel
import com.narvi.delivery.movie.presentation.ui.review.MovieReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {
    viewModel<HomeViewModel> { HomeViewModel(get(), get()) }
    viewModel<MyPageViewModel> { MyPageViewModel() }
    viewModel<MovieReviewsViewModel> { MovieReviewsViewModel() }
}