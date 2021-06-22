package com.narvi.delivery.movie.data.repository.datasourceimpl

import com.narvi.delivery.movie.data.api.UserApi
import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.data.repository.datasource.UserApiDataSource

class UserApiDataSourceImpl(
    private val userApi: UserApi
) : UserApiDataSource {

    override suspend fun saveUser(user: User): User {
        return userApi.saveUser(user)
    }
}