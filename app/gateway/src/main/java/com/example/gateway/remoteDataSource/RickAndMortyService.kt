package com.example.gateway.remoteDataSource

import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.APIResponseEpisode
import com.example.domain.entity.APIResponseLocation
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/")
    fun getAllCharacters(): Call<APIResponseCharacter>

    @GET("character/")
    fun getCharactersByPage(
        @Query("page") page: Int
    ): Call<APIResponseCharacter>

    @GET("location/")
    fun getAllLocations(): Single<APIResponseLocation>

    @GET("character/{id}")
    fun getOneCharacter(
        @Path("id") id: Int?
    ): Single<APIResponseCharacter>

    @GET("episode/{id}")
    fun getEpisode(
        @Path("id") id: Int?
    ): Single<APIResponseEpisode>
}