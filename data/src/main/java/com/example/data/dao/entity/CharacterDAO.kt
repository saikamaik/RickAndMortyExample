package com.example.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.domain.entity.CharacterModel
import com.example.domain.entity.Location
import com.example.domain.entity.Origin
import com.google.gson.Gson

@Entity
data class CharacterDAO(
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

fun CharacterModel.toCharacterDAO() =
    CharacterDAO(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin,
        location,
        image,
        url,
        created,
        episode
    )

fun CharacterDAO.toCharacterModel() =
    CharacterModel(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin,
        location,
        image,
        url,
        created,
        episode
    )

class TypeConverterLocation {
    val gson: Gson = Gson()
    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }
    @TypeConverter
    fun stringToLocation(data: String): Location {
        val name = data.substringBefore(',').substringAfter(":")
        val url = data.substringAfter(',')

        return Location(name, url)
    }

}

class TypeConverterOrigin {
    val gson: Gson = Gson()

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun stringToOrigin(data: String): Origin {
        val name = data.substringBefore(',').substringAfter(":")
        val url = data.substringAfter(',')

        return Origin(name, url)
//        if (data == null) return null
//        val listType: Type = object: TypeToken<Origin>() {}.type
//        return gson.fromJson(data, listType)
    }

}

