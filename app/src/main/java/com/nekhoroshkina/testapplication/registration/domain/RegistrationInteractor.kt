package com.nekhoroshkina.testapplication.registration.domain

import com.nekhoroshkina.testapplication.registration.domain.model.RegistrationResult
import com.nekhoroshkina.testapplication.registration.domain.model.User

interface RegistrationInteractor {

    suspend fun registerUser(user: User): RegistrationResult
}
