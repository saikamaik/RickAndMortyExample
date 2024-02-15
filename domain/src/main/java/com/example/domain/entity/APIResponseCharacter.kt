package com.example.domain.entity

data class APIResponseCharacter (
    var info: Info,
    var results: List<CharacterModel>
)

data class APIResponseLocation(
    var info: Info,
    var results: List<LocationModel>
)

data class APIResponseEpisode(
    var info: Info,
    var results: List<Episode>
)

data class Info (
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: String?
)