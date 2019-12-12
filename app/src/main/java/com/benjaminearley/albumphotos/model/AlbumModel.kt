package com.benjaminearley.albumphotos.model

import com.benjaminearley.albumphotos.*
import com.benjaminearley.albumphotos.repository.IAlbumRepository
import com.benjaminearley.albumphotos.repository.data.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AlbumModel(private val albumRepository: IAlbumRepository) : IAlbumModel {

    override fun getAlbums() = flow<Result<List<Album>>> {
        emit(Loading())
        try {
            emit(Success(albumRepository.getAlbums().take(10)))
        } catch (exception: Exception) {
            emit(Error(exception.message ?: getString(R.string.error)))
        }
    }.flowOn(Dispatchers.IO)
}

interface IAlbumModel {
    fun getAlbums(): Flow<Result<List<Album>>>
}