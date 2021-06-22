package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.data.api.MovieApi
import com.narvi.delivery.movie.data.api.MovieFirebaseApi
import com.narvi.delivery.movie.data.api.ReviewApi
import com.narvi.delivery.movie.data.api.ReviewFirebaseApi
import com.narvi.delivery.movie.data.repository.datasource.MovieApiDataSource
import com.narvi.delivery.movie.data.repository.datasource.ReviewApiDataSource
import com.narvi.delivery.movie.data.repository.datasourceimpl.MovieApiDataSourceImpl
import com.narvi.delivery.movie.data.repository.datasourceimpl.ReviewApiDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val dataSourceModule : Module = module {
    single<MovieApi> { MovieFirebaseApi(get()) }
    single<ReviewApi> { ReviewFirebaseApi(get()) }

    single<MovieApiDataSource> { MovieApiDataSourceImpl(get()) }
    single<ReviewApiDataSource> { ReviewApiDataSourceImpl(get()) }
}