package com.benjaminearley.albumphotos.ui.photos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.benjaminearley.albumphotos.*
import com.benjaminearley.albumphotos.model.PhotoModel
import com.benjaminearley.albumphotos.repository.PhotoRepository
import com.benjaminearley.albumphotos.repository.data.Photo
import com.benjaminearley.albumphotos.repository.network.TypicodeService
import com.google.android.material.snackbar.Snackbar

class PhotosFragment : Fragment() {

    companion object {

        private const val albumIdKey = "albumId"

        fun newInstance(albumId: String): PhotosFragment {
            val fragment = PhotosFragment()
            fragment.arguments = Bundle().apply { putString(albumIdKey, albumId) }
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().findViewById<Toolbar>(R.id.toolbar).navigationIcon = null
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.photos_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).apply {
            navigationIcon = loadDrawable(R.drawable.ic_arrow_back, R.color.white)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        val photosViewModel: PhotosViewModel by viewModels {
            PhotosViewModelFactory(
                arguments?.getString(albumIdKey),
                PhotoModel(
                    PhotoRepository(
                        TypicodeService.create(
                            AlbumPhotosApplication.instance.retrofit
                        )
                    )
                )
            )
        }

        val photoAdapter = PhotoAdapter()
        requireView().findViewById<RecyclerView>(R.id.photos).apply {
            addItemDecoration(divider)
            adapter = photoAdapter
        }

        photosViewModel.getPhotos().observe(viewLifecycleOwner, Observer<Result<List<Photo>>> {
            when (it) {
                is Loading -> Unit //TODO
                is Success -> photoAdapter.submitList(it.data)
                is Error -> Snackbar.make(requireView(), it.error, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}