package com.example.thindie.aston_intensive_lesson5.di

import com.example.thindie.aston_intensive_lesson5.data.ContactsRepositoryImpl
import com.example.thindie.aston_intensive_lesson5.domain.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ContactsRepositoryModule {
    @Binds
    fun bindRepository(impl: ContactsRepositoryImpl): ContactsRepository
}