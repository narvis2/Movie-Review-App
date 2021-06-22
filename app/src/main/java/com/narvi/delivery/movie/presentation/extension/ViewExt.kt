package com.narvi.delivery.movie.presentation.extension

import android.view.View
import androidx.annotation.Px
import com.narvi.delivery.movie.presentation.extension.dip

@Px
fun View.dip(dipValue: Float) = context.dip(dipValue)

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}
