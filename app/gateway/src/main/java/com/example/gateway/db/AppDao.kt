package com.example.gateway.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.CharacterModel

@Dao
interface AppDao {

    @Query("SELECT * FROM charactermodel")
    fun getAllCharacters(): LiveData<List<CharacterModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characterModel: CharacterModel)

    @Query("DELETE FROM charactermodel")
    fun deleteAllCharacters()

}