package com.narvi.delivery.movie.data.api

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_MOVIES
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_MOVIE_ID
import kotlinx.coroutines.tasks.await

class MovieFirebaseApi(
    private val firestore: FirebaseFirestore
) : MovieApi {

    override suspend fun getAllMovies(): List<Movie> {
        val result = firestore.collection(FIRESTORE_MOVIES)
            .get()
            .await()
            .map {
                it.toObject<Movie>()
            }

        return result
    }

    override suspend fun getMovies(movieIds: List<String>): List<Movie> {
        val result = firestore.collection(FIRESTORE_MOVIES)
            .whereIn(FieldPath.documentId(), movieIds)
            .get()
            .await()
            .map {
                it.toObject<Movie>()
            }

        return result
    }
}