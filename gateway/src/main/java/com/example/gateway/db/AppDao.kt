package com.example.gateway.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dao.entity.CharacterDAO
import com.example.data.dao.entity.EpisodeDAO

@Dao
interface AppDao {

    @Query("SELECT * FROM characterdao")
    fun getAllCharacters(): LiveData<List<CharacterDAO>>

    @Query("SELECT * FROM episodedao")
    fun getAllEpisodes(): LiveData<List<EpisodeDAO>>

    @Query("SELECT * FROM characterdao WHERE id = :characterId")
    fun getOneCharacters(characterId: Int): LiveData<CharacterDAO>

    @Query("SELECT * FROM episodedao WHERE id = :episodeId")
    fun getOneEpisode(episodeId: Int): LiveData<EpisodeDAO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characterDAO: CharacterDAO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEpisodes(episodeDAO: EpisodeDAO)

    @Query("DELETE FROM characterdao")
    fun deleteAllCharacters()

}