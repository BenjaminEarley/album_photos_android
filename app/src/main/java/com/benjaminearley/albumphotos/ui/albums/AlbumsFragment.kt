package com.benjaminearley.albumphotos.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.benjaminearley.albumphotos.*
import com.benjaminearley.albumphotos.model.AlbumModel
import com.benjaminearley.albumphotos.repository.AlbumRepository
import com.benjaminearley.albumphotos.repository.data.Album
import com.benjaminearley.albumphotos.repository.network.TypicodeService
import com.benjaminearley.albumphotos.ui.photos.PhotosFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG

class AlbumsFragment : Fragment() {

    companion object {
        const val albumListName = "albumList"

        fun newInstance() =
            AlbumsFragment()
    }

    private val albumsViewModel: AlbumsViewModel by viewModels {
        AlbumsViewModelFactory(
            AlbumModel(
                AlbumRepository(
                    TypicodeService.create(
                        AlbumPhotosApplication.instance.retrofit
                    )
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.albums_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val albumAdapter = AlbumAdapter { album -> onAlbumClick(album) }
        requireView().findViewById<RecyclerView>(R.id.albums).adapter = albumAdapter

        albumsViewModel.getAlbums().observe(viewLifecycleOwner, Observer<Result<List<Album>>> {
            when (it) {
                is Loading -> Unit //TODO
                is Success -> albumAdapter.submitList(it.data)
                is Error -> Snackbar.make(requireView(), it.error, LENGTH_LONG).show()
            }
        })
    }

    private fun onAlbumClick(album: Album) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .add(R.id.container, PhotosFragment.newInstance(album.id))
            .addToBackStack(albumListName)
            .commit()
    }
}
