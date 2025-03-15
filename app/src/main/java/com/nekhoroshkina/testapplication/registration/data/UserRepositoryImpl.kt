package com.nekhoroshkina.testapplication.registration.data

import com.nekhoroshkina.testapplication.registration.data.converter.userToUserEntity
import com.nekhoroshkina.testapplication.registration.data.db.UserDao
import com.nekhoroshkina.testapplication.registration.domain.UserRepository
import com.nekhoroshkina.testapplication.registration.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun isUserExists(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(userToUserEntity(user))
    }
}
