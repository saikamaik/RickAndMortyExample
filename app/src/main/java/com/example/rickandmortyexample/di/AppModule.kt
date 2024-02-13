package com.example.rickandmortyexample.di

import com.example.gateway.remoteDataSource.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val base_url: String = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideMainService(retrofit : Retrofit) : RickAndMortyService{
        return retrofit.create(RickAndMortyService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideMainRemoteData(mainService : RickAndMortyService) = CharacterRepository(mainService)
}