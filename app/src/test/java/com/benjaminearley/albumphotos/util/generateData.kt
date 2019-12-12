package com.benjaminearley.albumphotos.util

import com.benjaminearley.albumphotos.repository.data.Album
import com.benjaminearley.albumphotos.repository.data.Photo

val Album.Companion.randomizer: Randomizer<Album>
    get() =
        { random ->
            Album(
                id = String.randomizer(0, 20)(random),
                userId = String.randomizer(0, 20)(random),
                title = String.randomizer(0, 20)(random)
            )
        }

val Photo.Companion.randomizer: Randomizer<Photo>
    get() =
        { random ->
            Photo(
                id = String.randomizer(0, 20)(random),
                albumId = String.randomizer(0, 20)(random),
                title = String.randomizer(0, 20)(random),
                url = String.randomizer(0, 20)(random),
                thumbnailUrl = String.randomizer(0, 20)(random)
            )
        }
