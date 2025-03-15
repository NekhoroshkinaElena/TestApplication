package com.nekhoroshkina.testapplication.registration.domain

import com.nekhoroshkina.testapplication.registration.domain.model.User

interface UserRepository {
    suspend fun isUserExists(email: String): Boolean
    suspend fun saveUser(user: User)
}
