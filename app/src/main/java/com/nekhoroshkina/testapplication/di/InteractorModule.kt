package com.nekhoroshkina.testapplication.di

import com.nekhoroshkina.testapplication.registration.domain.RegistrationInteractor
import com.nekhoroshkina.testapplication.registration.domain.RegistrationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindRegistrationInteractor(registrationInteractorImpl: RegistrationInteractorImpl): RegistrationInteractor
}
