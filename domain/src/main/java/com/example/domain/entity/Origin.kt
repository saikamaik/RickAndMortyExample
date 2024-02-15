package com.example.domain.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

data class Origin(
    var name: String,
    var url: String
)

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
