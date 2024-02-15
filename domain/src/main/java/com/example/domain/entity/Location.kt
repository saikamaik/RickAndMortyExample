package com.example.domain.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

data class Location(
    var name: String,
    var url: String
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