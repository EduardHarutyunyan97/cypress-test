package com.example.cypress_test.album.repository

import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.album.service.AlbumService
import io.reactivex.rxjava3.core.Flowable

class AlbumRepository(private val albumService: AlbumService) {
    fun getAlbums(): Flowable<List<Album>> {
        return albumService.getAlbums()
    }
}