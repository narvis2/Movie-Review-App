package com.narvi.delivery.movie.presentation.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module = module {
    single { Dispatchers.IO }
    single { Firebase.firestore }
}