package com.example.gateway.remoteDataSource

import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.CharacterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) {

    fun makeApiCall(liveDataList: MutableLiveData<List<CharacterModel>>) {
        val call: Call<APIResponseCharacter> = rickAndMortyService.getAllCharacters()
        call.enqueue(object: Callback<APIResponseCharacter> {
            override fun onResponse(
                call: Call<APIResponseCharacter>,
                response: Response<APIResponseCharacter>
            ) {
                liveDataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<APIResponseCharacter>, t: Throwable) {
                liveDataList.postValue(null)
            }

        }
        )
    }

}