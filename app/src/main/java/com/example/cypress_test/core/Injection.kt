package com.example.cypress_test.core

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.cypress_test.MainViewModelFactory
import com.example.cypress_test.album.repository.AlbumRepository
import com.example.cypress_test.album.service.AlbumService
import com.example.cypress_test.photo.repository.PhotoRepository
import com.example.cypress_test.photo.service.PhotoService

object Injection {
    fun provideMainViewModelFactory(context: Context): ViewModelProvider.Factory {
        val apiService = ApiService(context)

        val albumService = apiService.createService(AlbumService::class.java)
        val photoService = apiService.createService(PhotoService::class.java)

        val albumRepo = AlbumRepository(albumService)
        val photoRepo = PhotoRepository(photoService)

        return MainViewModelFactory(albumRepo, photoRepo)
    }

}