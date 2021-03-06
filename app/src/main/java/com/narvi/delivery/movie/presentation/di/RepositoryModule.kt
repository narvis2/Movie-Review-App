package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.data.repository.MovieRepositoryImpl
import com.narvi.delivery.movie.data.repository.ReviewRepositoryImpl
import com.narvi.delivery.movie.data.repository.UserRepositoryImpl
import com.narvi.delivery.movie.domain.repository.MovieRepository
import com.narvi.delivery.movie.domain.repository.ReviewRepository
import com.narvi.delivery.movie.domain.repository.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
}