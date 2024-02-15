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

    private var page: Int = 1

    fun getAllCharacterData(): LiveData<List<CharacterModel>> {
        return repository.getAllCharactersFromDB()
    }

    fun loadNextPageOfData() {
        repository.getCharactersByPage(page)
        page++
    }

    private val _navigateToItemInfo = MutableLiveData<Int?>()
    val navigateToItemInfo: LiveData<Int?>
        get() = _navigateToItemInfo

    fun doneNavigating() {
        _navigateToItemInfo.value = null
    }

    fun onCardClicked(id: Int) {
        _navigateToItemInfo.value = id
    }


}