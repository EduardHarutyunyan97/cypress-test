package com.example.cypress_test.album.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.album.service.AlbumPagingSource
import com.example.cypress_test.album.service.AlbumService
import io.reactivex.rxjava3.core.Flowable

class AlbumRepository(private val albumService: AlbumService) {

    fun getAlbums(): Flowable<PagingData<Album>> {
        return Pager(
            config = PagingConfig(pageSize = 4),
            pagingSourceFactory = {
                AlbumPagingSource(albumService)
            }
        ).flowable
    }
}