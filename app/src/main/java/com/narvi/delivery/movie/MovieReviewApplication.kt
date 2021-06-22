package com.narvi.delivery.movie

import android.app.Application
import com.narvi.delivery.movie.presentation.di.*
import io.grpc.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MovieReviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )

            androidContext(this@MovieReviewApplication)

            modules(
                appModule,
                viewModelModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}