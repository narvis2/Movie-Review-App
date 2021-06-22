package com.narvi.delivery.movie.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.presentation.uitll.Constant.FIRESTORE_USERS
import kotlinx.coroutines.tasks.await

class UserFirebaseApi(
    private val firestore: FirebaseFirestore
) : UserApi {

    override suspend fun saveUser(user: User): User {
        val result = firestore.collection(FIRESTORE_USERS)
            .add(user)
            .await()
            .let {
                User(it.id)
            }

        return result
    }

}