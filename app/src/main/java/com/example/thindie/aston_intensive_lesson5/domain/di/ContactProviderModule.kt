package com.example.thindie.aston_intensive_lesson5.domain.di

import com.example.thindie.aston_intensive_lesson5.domain.ContactProvider
import com.example.thindie.aston_intensive_lesson5.domain.ContactsUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ContactProviderModule {
    @Binds
    fun bindContactProvider(case: ContactsUseCases): ContactProvider
}