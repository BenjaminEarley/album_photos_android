package com.benjaminearley.albumphotos

import com.benjaminearley.albumphotos.model.AlbumModel
import com.benjaminearley.albumphotos.model.PhotoModel
import com.benjaminearley.albumphotos.repository.IAlbumRepository
import com.benjaminearley.albumphotos.repository.IPhotoRepository
import com.benjaminearley.albumphotos.repository.data.Album
import com.benjaminearley.albumphotos.repository.data.Photo
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class ModelTest {

    @Test
    fun testAlbumsModelEmission() {

        Album
            .randomizer
            .listRandomizer(0, 20)
            .sequenceRandomizer()(Random(seed))
            .take(numTests)
            .forEach { albums ->
                val count = albums.count().coerceAtMost(AlbumModel.limit)
                val model = AlbumModel(FakeAlbumRepository(albums))
                runBlocking {
                    val flow = model.getAlbums().toList()
                    assertEquals(
                        true,
                        flow[0] is Loading
                    )
                    assertEquals(
                        true,
                        flow[1] is Success
                    )
                    assertEquals(
                        count,
                        (flow[1] as Success).data.count()
                    )
                }

            }
    }

    @Test
    fun testPhotosModelEmission() {

        Photo
            .randomizer
            .listRandomizer(0, 20)
            .sequenceRandomizer()(Random(seed))
            .take(numTests)
            .forEach { photos ->
                val count = photos.count().coerceAtMost(PhotoModel.limit)
                val model = PhotoModel(FakePhotoRepository(photos))
                runBlocking {
                    val flow = model.getPhotos("").toList()
                    assertEquals(
                        true,
                        flow[0] is Loading
                    )
                    assertEquals(
                        true,
                        flow[1] is Success
                    )
                    assertEquals(
                        count,
                        (flow[1] as Success).data.count()
                    )
                }

            }
    }

    companion object {
        private const val numTests: Int = 1_000
        private const val seed: Long = 1234
    }
}

class FakeAlbumRepository(private val albums: List<Album>) : IAlbumRepository {
    override suspend fun getAlbums(): List<Album> = albums
}

class FakePhotoRepository(private val photos: List<Photo>) : IPhotoRepository {
    override suspend fun getPhotos(albumId: String): List<Photo> = photos

}