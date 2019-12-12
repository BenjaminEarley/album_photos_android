package com.benjaminearley.albumphotos.repository

import com.benjaminearley.albumphotos.repository.data.Album
import com.benjaminearley.albumphotos.repository.network.TypicodeService

class AlbumRepository(private val service: TypicodeService) : IAlbumRepository {
    override suspend fun getAlbums(): List<Album> = service.getAlbums()
}

interface IAlbumRepository {
    suspend fun getAlbums(): List<Album>
}
