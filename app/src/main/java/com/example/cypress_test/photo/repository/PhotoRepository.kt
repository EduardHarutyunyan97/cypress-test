package com.example.cypress_test.photo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.cypress_test.photo.entity.Photo
import com.example.cypress_test.photo.service.PhotoPagingSource
import com.example.cypress_test.photo.service.PhotoService
import io.reactivex.rxjava3.core.Flowable

class PhotoRepository(private val photoService: PhotoService) {

    fun getPhotos(albumId: Long): Flowable<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = {
                PhotoPagingSource(
                    photoService = photoService,
                    albumId = albumId
                )
            }
        ).flowable
    }
}