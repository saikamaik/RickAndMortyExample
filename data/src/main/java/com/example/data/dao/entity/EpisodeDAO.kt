package com.example.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.domain.entity.Episode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class EpisodeDAO(
    @PrimaryKey val id: Int,
    val created: String,
    val episode: String,
    @ColumnInfo(name = "episode_name") val name: String,
    val url: String
)

fun EpisodeDAO.toEpisode() =
    Episode(
        id, created, episode, name, url
    )

fun Episode.toEpisodeDAO() =
    EpisodeDAO (
        id, created, episode, name, url
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
