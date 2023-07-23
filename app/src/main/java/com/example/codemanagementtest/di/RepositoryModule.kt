package com.example.codemanagementtest.di

import com.example.codemanagementtest.data.repository.LocalRepositoryImpl
import com.example.codemanagementtest.data.repository.NetworkRepositoryImpl
import com.example.codemanagementtest.domain.repository.LocalRepository
import com.example.codemanagementtest.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    @Singleton
    abstract fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}