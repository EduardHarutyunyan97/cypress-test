package com.example.cypress_test.photo.service

import com.example.cypress_test.photo.entity.Photo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("photos/")
    fun getPhotos(
        @Query("albumId") albumId: Long,
        @Query("_limit") limit: Int=3
    ): Single<Response<List<Photo>>>
}