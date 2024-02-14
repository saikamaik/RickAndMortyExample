package com.example.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class CharacterModel(

    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "status") var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    @ColumnInfo(name = "image") var image: String,
    var url: String,
    val created: String,
    var episode: List<String>
)

class TypeConverterEpisode {
    val gson: Gson = Gson()

    @TypeConverter
    fun fromEpisodes(episodes: List<String>): String {
        return gson.toJson(episodes)
    }

    @TypeConverter
    fun toEpisodes(episode: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(episode, type)
    }
}
