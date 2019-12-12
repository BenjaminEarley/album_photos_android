package com.benjaminearley.albumphotos.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.benjaminearley.albumphotos.AlbumPhotosApplication
import com.benjaminearley.albumphotos.R
import com.benjaminearley.albumphotos.divider
import com.benjaminearley.albumphotos.loadDrawable
import com.benjaminearley.albumphotos.repository.PhotoRepository
import com.benjaminearley.albumphotos.repository.network.TypicodeService
import com.google.android.material.snackbar.Snackbar

class PhotosFragment : Fragment() {

    companion object {

        private const val ALBUM_ID = "albumId"

        fun newInstance(albumId: String): PhotosFragment {
            val fragment = PhotosFragment()
            fragment.arguments = Bundle().apply { putString(ALBUM_ID, albumId) }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.photos_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).apply {
            navigationIcon = loadDrawable(R.drawable.ic_arrow_back, R.color.white)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
                navigationIcon = null
            }
        }

        val photosViewModel: PhotosViewModel by viewModels {
            PhotosViewModelFactory(
                arguments?.getString(ALBUM_ID),
                PhotoRepository(
                    TypicodeService.create(
                        AlbumPhotosApplication.instance.retrofit
                    )
                )
            )
        }

        val photoAdapter = PhotoAdapter()
        requireView().findViewById<RecyclerView>(R.id.photos).apply {
            addItemDecoration(divider)
            adapter = photoAdapter
        }

        photosViewModel.getPhotos().observe(viewLifecycleOwner, Observer<Result> {
            when (it) {
                Loading -> Unit //TODO
                is Success -> photoAdapter.submitList(it.photos)
                is Error -> Snackbar.make(requireView(), it.error, Snackbar.LENGTH_LONG).show()
            }
        })
    }


}