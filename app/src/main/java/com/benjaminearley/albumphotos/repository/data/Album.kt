package com.benjaminearley.albumphotos.repository.data

import androidx.recyclerview.widget.DiffUtil

data class Album(val id: String, val userId: String, val title: String) {
    companion object {
        val diffCallback: DiffUtil.ItemCallback<Album> =
            object : DiffUtil.ItemCallback<Album>() {
                override fun areItemsTheSame(
                    oldItem: Album,
                    newItem: Album
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Album,
                    newItem: Album
                ) = oldItem == newItem
            }
    }
}