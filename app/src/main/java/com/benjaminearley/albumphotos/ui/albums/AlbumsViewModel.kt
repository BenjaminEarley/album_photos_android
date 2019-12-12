package com.benjaminearley.albumphotos.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.benjaminearley.albumphotos.R
import com.benjaminearley.albumphotos.getString
import com.benjaminearley.albumphotos.repository.IAlbumRepository
import com.benjaminearley.albumphotos.repository.data.Album
import kotlinx.coroutines.Dispatchers

class AlbumsViewModel(private val repository: IAlbumRepository) : ViewModel() {

    private val albums = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            emit(Success(repository.getAlbums(limit = 10)))
        } catch (exception: Exception) {
            emit(Error(exception.message ?: getString(R.string.error)))
        }
    }

    fun getAlbums(): LiveData<Result> = albums
}

sealed class Result
object Loading : Result()
data class Success(val albums: List<Album>) : Result()
data class Error(val error: String) : Result()

@Suppress("UNCHECKED_CAST")
class AlbumsViewModelFactory(private val repository: IAlbumRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(repository) as T
    }

}