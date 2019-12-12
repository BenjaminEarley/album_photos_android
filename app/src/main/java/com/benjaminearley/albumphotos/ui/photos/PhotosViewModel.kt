package com.benjaminearley.albumphotos.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.benjaminearley.albumphotos.R
import com.benjaminearley.albumphotos.getString
import com.benjaminearley.albumphotos.repository.IPhotoRepository
import com.benjaminearley.albumphotos.repository.data.Photo
import kotlinx.coroutines.Dispatchers

class PhotosViewModel(private val albumId: String?, private val repository: IPhotoRepository) :
    ViewModel() {

    private val photos = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            val albumId = albumId ?: throw UnsupportedOperationException()
            emit(Success(repository.getPhotos(albumId, limit = 10)))
        } catch (exception: Exception) {
            emit(Error(exception.message ?: getString(R.string.error)))
        }
    }

    fun getPhotos(): LiveData<Result> = photos
}

sealed class Result
object Loading : Result()
data class Success(val photos: List<Photo>) : Result()
data class Error(val error: String) : Result()

@Suppress("UNCHECKED_CAST")
class PhotosViewModelFactory(
    private val albumId: String?,
    private val repository: IPhotoRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(albumId, repository) as T
    }

}