package com.example.cypress_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cypress_test.album.repository.AlbumRepository
import com.example.cypress_test.photo.repository.PhotoRepository


class MainViewModelFactory(
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(albumRepository, photoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}