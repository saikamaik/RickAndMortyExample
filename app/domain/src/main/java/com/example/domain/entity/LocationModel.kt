package com.example.domain.entity

data class LocationModel(

    var id: Int,
    var name: String?,
    var url: String,
    var type: String?,
    var dimension: String?,
    var residents: List<String>

)
