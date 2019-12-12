package com.benjaminearley.albumphotos.model

import com.benjaminearley.albumphotos.*
import com.benjaminearley.albumphotos.repository.IPhotoRepository
import com.benjaminearley.albumphotos.repository.data.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PhotoModel(private val photoRepository: IPhotoRepository) : IPhotoModel {

    override fun getPhotos(albumId: String?) = flow<Result<List<Photo>>> {
        emit(Loading())
        try {
            val photos =
                photoRepository
                    .getPhotos(albumId ?: throw UnsupportedOperationException())
                    .take(limit)
            emit(Success(photos))
        } catch (exception: Exception) {
            emit(Error(exception.message ?: getString(R.string.error)))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        const val limit = 10
    }
}

interface IPhotoModel {
    fun getPhotos(albumId: String?): Flow<Result<List<Photo>>>
}