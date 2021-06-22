package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.domain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule : Module = module {
    factory<GetAllMoviesUseCase> { GetAllMoviesUseCase(get()) }
    factory<GetRandomFeaturedMovieUseCase> { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory<GetAllMovieReviewsUseCase> { GetAllMovieReviewsUseCase(get(), get()) }
    factory<GetMyReviewedMoviesUseCase> { GetMyReviewedMoviesUseCase(get(), get(), get()) }
    factory<SubmitReviewUseCase> { SubmitReviewUseCase(get(), get()) }
    factory<DeleteReviewUseCase> { DeleteReviewUseCase(get()) }
}