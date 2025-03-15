package com.nekhoroshkina.testapplication.di

import com.nekhoroshkina.testapplication.registration.data.UserRepositoryImpl
import com.nekhoroshkina.testapplication.registration.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRegistrationRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
