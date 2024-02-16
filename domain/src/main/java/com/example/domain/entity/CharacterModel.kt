package com.example.domain.entity

data class CharacterModel(

    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    var image: String,
    var url: String,
    val created: String,
    var episode: List<String>
)

