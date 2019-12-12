package com.benjaminearley.albumphotos.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.benjaminearley.albumphotos.Result
import com.benjaminearley.albumphotos.model.IAlbumModel
import com.benjaminearley.albumphotos.repository.data.Album

class AlbumsViewModel(model: IAlbumModel) : ViewModel() {

    private val albums = model.getAlbums().asLiveData()

    fun getAlbums(): LiveData<Result<List<Album>>> = albums
}

@Suppress("UNCHECKED_CAST")
class AlbumsViewModelFactory(private val model: IAlbumModel) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(model) as T
    }

}