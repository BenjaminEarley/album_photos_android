package com.benjaminearley.albumphotos.ui.albums

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benjaminearley.albumphotos.R
import com.benjaminearley.albumphotos.inflate
import com.benjaminearley.albumphotos.repository.data.Album

class AlbumAdapter(
    private val tapListener: (Album) -> Unit
) : ListAdapter<Album, AlbumViewHolder>(Album.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(parent.inflate(R.layout.albums_list_item))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val holderTapListener: (Int) -> Unit = { getItem(it)?.let(tapListener) }
        getItem(position)?.let { holder.bindTo(it, holderTapListener) } ?: holder.clear()
    }
}

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun clear() =
        with(itemView) {
            setOnClickListener(null)
        }

    fun bindTo(album: Album, tapListener: (Int) -> Unit) =
        with(itemView) {
            val title: TextView = findViewById(R.id.title)
            title.text = album.title
            setOnClickListener { tapListener(adapterPosition) }
        }
}