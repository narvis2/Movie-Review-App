package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.domain.usecase.GetAllMoviesUseCase
import com.narvi.delivery.movie.domain.usecase.GetRandomFeaturedMovieUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule : Module = module {
    factory<GetAllMoviesUseCase> { GetAllMoviesUseCase(get()) }
    factory<GetRandomFeaturedMovieUseCase> { GetRandomFeaturedMovieUseCase(get(), get()) }
}