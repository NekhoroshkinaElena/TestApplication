package com.nekhoroshkina.testapplication.registration.domain.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
