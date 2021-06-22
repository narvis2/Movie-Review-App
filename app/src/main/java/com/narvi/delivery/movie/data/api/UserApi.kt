package com.narvi.delivery.movie.data.api

import com.narvi.delivery.movie.data.model.User


interface UserApi {

    suspend fun saveUser(user: User) : User
}