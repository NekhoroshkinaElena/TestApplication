package com.nekhoroshkina.testapplication.registration.data.converter

import com.nekhoroshkina.testapplication.registration.data.db.UserEntity
import com.nekhoroshkina.testapplication.registration.domain.model.User

fun userToUserEntity(user: User): UserEntity {
    return UserEntity(user.email, user.name, user.password)
}
