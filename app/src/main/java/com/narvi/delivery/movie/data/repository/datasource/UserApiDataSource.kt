package com.narvi.delivery.movie.data.repository.datasource

import com.narvi.delivery.movie.data.model.User

interface UserApiDataSource {

    suspend fun saveUser(user : User) : User
}