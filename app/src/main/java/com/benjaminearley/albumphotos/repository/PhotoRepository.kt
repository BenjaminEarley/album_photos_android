package com.benjaminearley.albumphotos.repository

import com.benjaminearley.albumphotos.repository.data.Photo
import com.benjaminearley.albumphotos.repository.network.TypicodeService

class PhotoRepository(private val service: TypicodeService) : IPhotoRepository {
    override suspend fun getPhotos(albumId: String): List<Photo> =
        service.getPhotos(albumId)
}

interface IPhotoRepository {
    suspend fun getPhotos(albumId: String): List<Photo>
}
