package com.example.cypress_test.photo.entity

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("albumId")
    val albumId: Long,

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)