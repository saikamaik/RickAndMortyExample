package com.example.gateway.remoteDataSource

import androidx.lifecycle.LiveData
import com.example.domain.entity.APIResponseCharacter
import com.example.domain.entity.APIResponseEpisode
import com.example.domain.entity.CharacterModel
import com.example.domain.entity.Episode
import com.example.gateway.db.AppDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService,
    private val appDao: AppDao
) {

    fun getAllCharactersFromDB(): LiveData<List<CharacterModel>>{
        return appDao.getAllCharacters()
    }

    fun getOneCharacterFromDB(id: Int): LiveData<CharacterModel>{
        return appDao.getOneCharacters(id)
    }

    fun getOneEpisodeFromDB(episodeId: Int) : LiveData<Episode> {
        return appDao.getOneEpisode(episodeId)
    }

    fun insertCharacters(characterModel: CharacterModel) {
        appDao.insertCharacters(characterModel)
    }

    fun insertEpisodes(episode: Episode) {
        appDao.insertEpisodes(episode)
    }

    fun getCharactersByPage(page: Int) {
        val call: Call<APIResponseCharacter> = rickAndMortyService.getCharactersByPage(page)
        call.enqueue(object : Callback<APIResponseCharacter> {
            override fun onResponse(
                call: Call<APIResponseCharacter>,
                response: Response<APIResponseCharacter>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.forEach {
                        insertCharacters(it)
                    }
                }
            }
            override fun onFailure(call: Call<APIResponseCharacter>, t: Throwable) {

            }
        }
        )
    }

    fun getAllEpisodesByPage(page: Int) {
        val call: Call<APIResponseEpisode> = rickAndMortyService.getEpisodes(page)
        call.enqueue(object : Callback<APIResponseEpisode> {
            override fun onResponse(
                call: Call<APIResponseEpisode>,
                response: Response<APIResponseEpisode>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.forEach {
                        insertEpisodes(it)
                    }
                }
            }
            override fun onFailure(call: Call<APIResponseEpisode>, t: Throwable) {

            }
        }
        )
    }

}