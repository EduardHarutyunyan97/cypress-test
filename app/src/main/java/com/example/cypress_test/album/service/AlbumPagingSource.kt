package com.example.cypress_test.album.service

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.cypress_test.album.entity.Album
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io

class AlbumPagingSource(
    private val albumService: AlbumService
) : RxPagingSource<Int, Album>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Album>> {
        val position = params.key ?: 1

        return albumService.getAlbums()
            .subscribeOn(io())
            .map { toLoadResult(it, position) }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? = state.anchorPosition

    private fun toLoadResult(data: List<Album>, position: Int): LoadResult<Int, Album> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = position + 1
        )
    }
}