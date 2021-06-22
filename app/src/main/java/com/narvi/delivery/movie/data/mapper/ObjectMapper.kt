package com.narvi.delivery.movie.data.mapper

import com.narvi.delivery.movie.data.model.Review

fun List<Review>.toListString() : List<String> =
    map {
        it.movieId!!
    }

