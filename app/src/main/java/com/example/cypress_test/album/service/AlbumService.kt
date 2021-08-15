package com.example.cypress_test.album.service

import com.example.cypress_test.album.entity.Album
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("albums/")
    fun getAlbums(
        @Query("userId") userId: Long = 1,
        @Query("_limit") limit: Int = 4
    ): Flowable<List<Album>>
}