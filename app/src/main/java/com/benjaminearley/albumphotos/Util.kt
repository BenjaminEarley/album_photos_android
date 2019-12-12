package com.benjaminearley.albumphotos

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

fun getString(@StringRes stringRes: Int): String =
    AlbumPhotosApplication.instance.getString(stringRes)

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

val divider =
    DividerItemDecoration(
        AlbumPhotosApplication.instance,
        LinearLayoutManager.VERTICAL
    ).apply {
        ContextCompat.getDrawable(AlbumPhotosApplication.instance, R.drawable.divider_space)
            ?.let { setDrawable(it) }
    }

fun loadDrawable(@DrawableRes id: Int, @ColorRes tint: Int): Drawable? =
    ContextCompat
        .getDrawable(AlbumPhotosApplication.instance, id)
        ?.let { drawable ->
            DrawableCompat
                .wrap(drawable)
                .mutate()
                .also {
                    DrawableCompat.setTint(
                        it,
                        ContextCompat.getColor(AlbumPhotosApplication.instance, tint)
                    )
                }
        }

sealed class Result<T>
class Loading<T> : Result<T>()
data class Success<T>(val data: T) : Result<T>()
data class Error<T>(val error: String) : Result<T>()