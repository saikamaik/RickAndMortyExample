package com.example.gateway.remoteDataSource

import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.APIResponseEpisode
import com.example.domain.entity.APIResponseLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/")
    fun getCharactersByPage(
        @Query("page") page: Int
    ): Call<APIResponseCharacter>

    @GET("episode/")
    fun getEpisodes(
        @Query("page") page: Int
    ): Call<APIResponseEpisode>
}