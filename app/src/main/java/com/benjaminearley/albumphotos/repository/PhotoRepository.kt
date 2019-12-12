package com.benjaminearley.albumphotos.repository

import com.benjaminearley.albumphotos.repository.data.Photo
import com.benjaminearley.albumphotos.repository.network.TypicodeService

class PhotoRepository(private val service: TypicodeService) : IPhotoRepository {
    override suspend fun getPhotos(albumId: String, limit: Int): List<Photo> =
        service.getPhotos(albumId).take(limit)
}

interface IPhotoRepository {
    suspend fun getPhotos(albumId: String, limit: Int): List<Photo>
}
