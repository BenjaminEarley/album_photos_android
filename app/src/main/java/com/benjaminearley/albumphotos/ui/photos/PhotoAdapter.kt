package com.benjaminearley.albumphotos.ui.photos

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benjaminearley.albumphotos.AlbumPhotosApplication
import com.benjaminearley.albumphotos.R
import com.benjaminearley.albumphotos.inflate
import com.benjaminearley.albumphotos.repository.data.Photo

class PhotoAdapter : ListAdapter<Photo, PhotoViewHolder>(Photo.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(parent.inflate(R.layout.photos_list_item))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) } ?: holder.clear()
    }
}

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun clear() = with(itemView) {
        setOnClickListener(null)
    }

    fun bindTo(photo: Photo) = with(itemView) {
        val title: TextView = findViewById(R.id.title)
        title.text = photo.title
        val image: ImageView = findViewById(R.id.card_image)
        AlbumPhotosApplication.instance.picasso.load(photo.url).into(image)
    }
}