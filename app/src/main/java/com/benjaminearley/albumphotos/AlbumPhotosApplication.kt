package com.benjaminearley.albumphotos

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AlbumPhotosApplication : Application() {

    lateinit var picasso: Picasso
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        instance = this

        val okhttp = OkHttpClient()

        picasso = Picasso.Builder(instance).downloader(OkHttp3Downloader(okhttp)).build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .client(okhttp)
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    companion object {
        lateinit var instance: AlbumPhotosApplication

    }
}