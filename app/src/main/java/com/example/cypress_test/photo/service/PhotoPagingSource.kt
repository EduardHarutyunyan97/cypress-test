package com.example.cypress_test.photo.service

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.cypress_test.photo.entity.Photo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io

class PhotoPagingSource(
    private val photoService: PhotoService,
    private val albumId: Long,
) : RxPagingSource<Int, Photo>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Photo>> {
        val position = params.key ?: 1
        return photoService.getPhotos(albumId)
            .subscribeOn(io())
            .onErrorReturn { listOf() }
            .map { toLoadResult(it, position) }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = state.anchorPosition


    private fun toLoadResult(data: List<Photo>, position: Int): LoadResult<Int, Photo> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = position + 1
        )
    }
}