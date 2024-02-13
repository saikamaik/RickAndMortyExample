package com.example.domain.entity

data class CharacterModel(

    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var origin: LocationModel,
    var location: LocationModel,
    var imageURL: String,
    var url: String,
    val created: String,
    var episode: List<String>

)
