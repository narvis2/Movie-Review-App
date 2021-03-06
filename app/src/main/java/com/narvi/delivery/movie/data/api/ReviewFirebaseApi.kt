package com.narvi.delivery.movie.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_MOVIES
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_MOVIE_ID
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_REVIEWS
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_REVIEW_CREATEDAT
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_REVIEW_USERID
import kotlinx.coroutines.tasks.await

class ReviewFirebaseApi(
    private val firestore: FirebaseFirestore
) : ReviewApi {
    override suspend fun getLatestReview(movieId: String): Review? {
        val result = firestore.collection(FIRESTORE_REVIEWS)
            .whereEqualTo(FIRESTORE_MOVIE_ID, movieId)
            .orderBy(FIRESTORE_REVIEW_CREATEDAT, Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .map {
                it.toObject<Review>()
            }
            .firstOrNull()

        return result
    }

    override suspend fun getAllMovieReviews(movieId: String): List<Review> {
        val result = firestore.collection(FIRESTORE_REVIEWS)
            .whereEqualTo(FIRESTORE_MOVIE_ID, movieId)
            .orderBy(FIRESTORE_REVIEW_CREATEDAT, Query.Direction.DESCENDING)
            .get()
            .await()
            .map {
                it.toObject<Review>()
            }

        return result
    }

    override suspend fun getAllUserReviews(userId: String): List<Review> {
        val result = firestore.collection(FIRESTORE_REVIEWS)
            .whereEqualTo(FIRESTORE_REVIEW_USERID, userId)
            .orderBy(FIRESTORE_REVIEW_CREATEDAT, Query.Direction.DESCENDING)
            .get()
            .await()
            .map {
                it.toObject<Review>()
            }

        return result
    }

    override suspend fun addReview(review: Review): Review {
        val newReviewReference = firestore.collection(FIRESTORE_REVIEWS).document()
        val movieReference = firestore.collection(FIRESTORE_MOVIES).document(review.movieId!!)

        firestore.runTransaction { transaction ->
            val movie = transaction.get(movieReference).toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = oldNumberOfScore + 1
            val newAverageScore = (oldTotalScore + (review.score ?: 0f)) / newNumberOfScore

            transaction.set(
                movieReference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transaction.set(
                newReviewReference,
                review,
                SetOptions.merge()
            )
        }.await()

        return newReviewReference.get().await().toObject<Review>()!!
    }

    override suspend fun removeReview(review: Review) {
        val reviewReference = firestore.collection(FIRESTORE_REVIEWS).document(review.id!!)
        val movieReference = firestore.collection(FIRESTORE_MOVIES).document(review.movieId!!)

        firestore.runTransaction { transaction ->
            val movie = transaction
                .get(movieReference)
                .toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = (oldNumberOfScore - 1).coerceAtLeast(0)
            val newAverageScore = if (newNumberOfScore > 0) {
                (oldTotalScore - (review.score ?: 0f)) / newNumberOfScore
            } else {
                0f
            }

            transaction.set(
                movieReference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transaction.delete(reviewReference)
        }.await()
    }
}