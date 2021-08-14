package com.example.cypress_test.album.entity

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,
)