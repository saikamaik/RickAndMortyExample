package com.example.rickandmortyexample.presentation.mainpage

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

    private var liveDataList: MutableLiveData<List<CharacterModel>?> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<CharacterModel>?> {
        return liveDataList
    }

//    fun clearLiveData() {
//        liveDataList = MutableLiveData()
//        repository.makeApiCall(liveDataList)
//    }

    fun loadListOfData() {
        repository.makeApiCall(liveDataList)
    }

    fun loadNextPageOfData(page: Int){
        repository.getCharactersByPage(liveDataList, page)
    }

}