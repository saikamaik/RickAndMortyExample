package com.example.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class Episode(
    @PrimaryKey val id: Int,
    val created: String,
    val episode: String,
    @ColumnInfo(name = "episode_name") val name: String,
    val url: String
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
