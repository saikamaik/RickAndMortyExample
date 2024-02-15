package com.example.rickandmortyexample.presentation.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.CharacterModel
import com.example.domain.entity.Episode
import com.example.gateway.remoteDataSource.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemInfoViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    fun getOneCharacterFromDB(id: Int): LiveData<CharacterModel> {
        return repository.getOneCharacterFromDB(id)
    }

    fun getAllEpisodes(page: Int) {
        repository.getAllEpisodesByPage(page)
    }

    fun getOneEpisodeFromDB(id: Int): LiveData<Episode>{
        return repository.getOneEpisodeFromDB(id)
    }



}