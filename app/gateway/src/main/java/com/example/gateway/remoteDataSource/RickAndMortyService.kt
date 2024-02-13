package com.example.gateway.remoteDataSource

import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.APIResponseLocation
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character/")
    fun getAllCharacters(): Call<APIResponseCharacter>

    @GET("location/")
    fun getAllLocations(): Single<APIResponseLocation>

    @GET("character/{id}")
    fun getOneCharacter(
        @Path("id") id: Int?
    ): Single<APIResponseCharacter>
}