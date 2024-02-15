package com.example.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

