package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.domain.usecase.GetAllMovieReviewsUseCase
import com.narvi.delivery.movie.domain.usecase.GetAllMoviesUseCase
import com.narvi.delivery.movie.domain.usecase.GetMyReviewedMoviesUseCase
import com.narvi.delivery.movie.domain.usecase.GetRandomFeaturedMovieUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule : Module = module {
    factory<GetAllMoviesUseCase> { GetAllMoviesUseCase(get()) }
    factory<GetRandomFeaturedMovieUseCase> { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory<GetAllMovieReviewsUseCase> { GetAllMovieReviewsUseCase(get()) }
    factory<GetMyReviewedMoviesUseCase> { GetMyReviewedMoviesUseCase(get(), get(), get()) }
}