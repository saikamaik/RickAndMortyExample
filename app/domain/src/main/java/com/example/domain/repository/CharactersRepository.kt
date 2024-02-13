package com.example.domain.repository

import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.CharacterModel
import io.reactivex.Single

interface CharactersRepository {

    suspend fun getAllCharacters(page: Int) : List<APIResponseCharacter>

    suspend fun getOneCharacter(id: Int): CharacterModel


}