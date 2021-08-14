package com.example.cypress_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cypress_test.album.service.AlbumService
import com.example.cypress_test.core.ApiService
import com.example.cypress_test.photo.service.PhotoService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiService()
        val albumService = apiService.createService(AlbumService::class.java)

        albumService.getAlbums().subscribe({

        }, {

        })

        val photoService = apiService.createService(PhotoService::class.java)

        photoService.getPhotos(1).subscribe()
    }
}