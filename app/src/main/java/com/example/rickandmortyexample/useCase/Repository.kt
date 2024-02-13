package com.example.rickandmortyexample.useCase

import com.example.gateway.remoteDataSource.RickAndMortyService

class Repository (
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getAllCharacters() = rickAndMortyService.getAllCharacters()
}