package com.example.linkedinclone.common.di

import com.example.linkedinclone.common.network.ApiClient
import com.example.linkedinclone.common.repository.DummyJsonRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDummyRepo(apiClient: ApiClient): DummyJsonRepo = DummyJsonRepo(apiClient)

}