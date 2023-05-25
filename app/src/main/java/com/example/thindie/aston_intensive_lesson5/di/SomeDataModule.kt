package com.example.thindie.aston_intensive_lesson5.di

import com.example.thindie.aston_intensive_lesson5.data.someData.SomeDataHolder
import com.example.thindie.aston_intensive_lesson5.data.someData.SomeDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SomeDataModule {

    @Binds
    fun bindSomeData(someDataImpl: SomeDataImpl): SomeDataHolder
}