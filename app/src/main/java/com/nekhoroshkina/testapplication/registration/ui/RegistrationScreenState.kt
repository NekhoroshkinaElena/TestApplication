package com.nekhoroshkina.testapplication.registration.ui

sealed class RegistrationScreenState {
    data object Success : RegistrationScreenState()
    data class Error(val error: String) : RegistrationScreenState()
}
