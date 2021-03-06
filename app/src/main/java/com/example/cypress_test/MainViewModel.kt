package com.example.cypress_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.album.repository.AlbumRepository
import com.example.cypress_test.album.ui.AlbumUiModel
import com.example.cypress_test.photo.entity.Photo
import com.example.cypress_test.photo.repository.PhotoRepository
import com.example.cypress_test.photo.ui.PhotoAdapter
import io.reactivex.rxjava3.core.Flowable

class MainViewModel(
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    fun getAlbums(): Flowable<List<Album>> {
        return albumRepository.getAlbums()
    }

    fun getPhotosByAlbumIds(
        list: List<AlbumUiModel>,
        block: (Flowable<PagingData<Photo>>, PhotoAdapter) -> Unit
    ) {
        list.forEach {
            block(
                photoRepository.getPhotos(it.album.id).cachedIn(viewModelScope),
                it.photoAdapter,
            )
        }
    }
}