package com.benjaminearley.albumphotos.repository.data

import androidx.recyclerview.widget.DiffUtil

data class Photo(
    val id: String,
    val albumId: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) {
    companion object {
        val diffCallback: DiffUtil.ItemCallback<Photo> =
            object : DiffUtil.ItemCallback<Photo>() {
                override fun areItemsTheSame(
                    oldItem: Photo,
                    newItem: Photo
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Photo,
                    newItem: Photo
                ) = oldItem == newItem
            }
    }
}