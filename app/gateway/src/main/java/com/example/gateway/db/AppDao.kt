package com.example.gateway.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.CharacterModel
import com.example.domain.entity.Episode

@Dao
interface AppDao {

    @Query("SELECT * FROM charactermodel")
    fun getAllCharacters(): LiveData<List<CharacterModel>>

    @Query("SELECT * FROM episode")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Query("SELECT * FROM charactermodel WHERE id = :characterId")
    fun getOneCharacters(characterId: Int): LiveData<CharacterModel>

    @Query("SELECT * FROM episode WHERE id = :episodeId")
    fun getOneEpisode(episodeId: Int): LiveData<Episode>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characterModel: CharacterModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEpisodes(episode: Episode)

    @Query("DELETE FROM charactermodel")
    fun deleteAllCharacters()

}