package com.example.cypress_test.album.ui

import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.photo.ui.PhotoAdapter

data class AlbumUiModel(
    val album: Album,
    val photoAdapter: PhotoAdapter
)