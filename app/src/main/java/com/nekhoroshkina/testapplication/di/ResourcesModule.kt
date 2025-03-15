package com.nekhoroshkina.testapplication.di

import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Binds
    abstract fun bindStringProvider(stringProviderImpl: StringProviderImpl): StringProvider
}
