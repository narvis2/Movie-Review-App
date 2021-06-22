package com.narvi.delivery.movie.domain.repository

import com.narvi.delivery.movie.data.model.User

interface UserRepository {

    suspend fun getUser() : User?

    suspend fun saveUser(user: User)
}