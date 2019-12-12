package com.benjaminearley.albumphotos.repository.network

import com.benjaminearley.albumphotos.repository.data.Album
import com.benjaminearley.albumphotos.repository.data.Photo
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TypicodeService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}/photos")
    suspend fun getPhotos(@Path("id") albumId: String): List<Photo>

    companion object {
        fun create(retrofit: Retrofit) = retrofit.create(TypicodeService::class.java)
    }
}