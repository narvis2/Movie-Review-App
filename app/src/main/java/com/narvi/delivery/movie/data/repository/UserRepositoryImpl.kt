package com.narvi.delivery.movie.data.repository

import com.narvi.delivery.movie.data.model.User
import com.narvi.delivery.movie.data.preference.PreferenceManager
import com.narvi.delivery.movie.data.repository.datasource.UserApiDataSource
import com.narvi.delivery.movie.domain.repository.UserRepository
import com.narvi.delivery.movie.presentation.uitll.Constant.KEY_USER_ID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userApiDataSource: UserApiDataSource,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun getUser(): User? = withContext(dispatcher) {
        preferenceManager.getString(KEY_USER_ID)?.let {
            User(it)
        }
    }

    override suspend fun saveUser(user: User) {
        val newUser = userApiDataSource.saveUser(user)
        preferenceManager.setString(KEY_USER_ID, newUser.id!!)
    }
}