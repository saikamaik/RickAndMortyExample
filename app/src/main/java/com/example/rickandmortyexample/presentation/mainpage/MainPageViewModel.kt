package com.example.rickandmortyexample.presentation.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.CharacterModel
import com.example.gateway.remoteDataSource.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    var liveDataList: MutableLiveData<List<CharacterModel>?> = MutableLiveData()

    fun getAllCharacterData(): LiveData<List<CharacterModel>> {
        return repository.getAllCharactersFromDB()
    }

    fun loadNextPageOfData(page: Int){
        repository.getCharactersByPage(liveDataList, page)
    }

}