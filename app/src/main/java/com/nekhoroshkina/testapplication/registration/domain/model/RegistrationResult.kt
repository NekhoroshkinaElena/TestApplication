package com.nekhoroshkina.testapplication.registration.domain.model

sealed class RegistrationResult {
    data object Success : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}
