package com.narvi.delivery.movie.presentation.uitll

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.presentation.extension.toAbbreviatedString
import com.narvi.delivery.movie.presentation.extension.toDecimalFormatString

@BindingAdapter("setImage")
fun ImageView.setImage(url: String) {
    GlideApp.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("countText")
fun TextView.countText(number : Int?) {
    text = number?.toAbbreviatedString()
}

@BindingAdapter("averageText")
fun TextView.averageScore(number : Float?) {
    text = number?.toDecimalFormatString("0.0")
}

@BindingAdapter("latestReview")
fun TextView.latestReview(review: Review?) {
    review?.let {
        text = ("\""+it.content + "\"")
    }
}

@BindingAdapter("latestLabel")
fun TextView.latestLabel(review: Review?) {
    review?.let {
        text = if (it.userId.isNullOrBlank()) {
            "๐ ๋ฐ๋๋ฐ๋ํ ํ๊ธฐ"
        } else {
                ("-" +it.userId.take(3)+"***"+ "-")
        }
    }
}

@BindingAdapter("releaseYear", "country")
fun TextView.additionalInformation(year: Int, country: String) {
    text = ("$yearยท$country")
}

@BindingAdapter("director", "actors")
fun TextView.setDirectorAndActors(director: String, actors: String) {
    text = ("๊ฐ๋ : $director\n์ถ์ฐ์ง : $actors")
}

@BindingAdapter("chipOperation")
fun ChipGroup.chipOperation(genre: String?) {
    removeAllViews()
    genre?.split(" ")?.forEach { genres ->
        addView(
            Chip(this.context).apply {
                isClickable = false
                text = genres
            }
        )
    }
}

@BindingAdapter("setAuthorId")
fun TextView.setAuthorId(userId: String?) {
    text = (userId?.take(3)+"***")
}

@BindingAdapter("averageScore", "numberOfScore")
fun TextView.setScoreText(averageScore : Float?, numberOfScope: Int?) {
    text = ("ํ์?" + " " + averageScore?.toDecimalFormatString("0.0") +" "+ "(${numberOfScope?.toAbbreviatedString()})")
}