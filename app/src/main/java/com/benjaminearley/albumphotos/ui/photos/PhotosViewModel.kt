package com.benjaminearley.albumphotos.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.benjaminearley.albumphotos.Result
import com.benjaminearley.albumphotos.model.IPhotoModel
import com.benjaminearley.albumphotos.repository.data.Photo

class PhotosViewModel(albumId: String?, model: IPhotoModel) :
    ViewModel() {

    private val photos = model.getPhotos(albumId).asLiveData()

    fun getPhotos(): LiveData<Result<List<Photo>>> = photos
}

@Suppress("UNCHECKED_CAST")
class PhotosViewModelFactory(
    private val albumId: String?,
    private val model: IPhotoModel
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(albumId, model) as T
    }

}