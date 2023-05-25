package com.example.thindie.aston_intensive_lesson5.data.di

import com.github.javafaker.Faker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FakerModule {
    @Provides
    fun provideFaker(): Faker {
        return Faker()
    }
}