package com.narvi.delivery.movie.presentation.di

import com.narvi.delivery.movie.data.api.*
import com.narvi.delivery.movie.data.preference.PreferenceManager
import com.narvi.delivery.movie.data.repository.datasource.MovieApiDataSource
import com.narvi.delivery.movie.data.repository.datasource.ReviewApiDataSource
import com.narvi.delivery.movie.data.repository.datasource.UserApiDataSource
import com.narvi.delivery.movie.data.repository.datasourceimpl.MovieApiDataSourceImpl
import com.narvi.delivery.movie.data.repository.datasourceimpl.ReviewApiDataSourceImpl
import com.narvi.delivery.movie.data.repository.datasourceimpl.UserApiDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val dataSourceModule : Module = module {
    single<MovieApi> { MovieFirebaseApi(get()) }
    single<ReviewApi> { ReviewFirebaseApi(get()) }
    single<UserApi> { UserFirebaseApi(get()) }

    single<PreferenceManager> { PreferenceManager(androidContext()) }

    single<MovieApiDataSource> { MovieApiDataSourceImpl(get()) }
    single<ReviewApiDataSource> { ReviewApiDataSourceImpl(get()) }
    single<UserApiDataSource> { UserApiDataSourceImpl(get()) }
}