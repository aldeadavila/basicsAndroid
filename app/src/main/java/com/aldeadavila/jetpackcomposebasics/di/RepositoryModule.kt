package com.aldeadavila.jetpackcomposebasics.di

import com.aldeadavila.jetpackcomposebasics.datasource.RestDataSource
import com.aldeadavila.jetpackcomposebasics.repository.UserRepository
import com.aldeadavila.jetpackcomposebasics.repository.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun userRepository(repo:UserRepositoryImp): UserRepository

}

