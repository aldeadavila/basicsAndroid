package com.aldeadavila.jetpackcomposebasics.model

data class User (
    val title: String,
    val name: String,
    val lastName: String,
    val city: String,
    val state: String,
    val thumbnail: String,
    val large: String,
    val medium: String,
    var id: Int = 0
)