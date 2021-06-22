package com.narvi.delivery.movie.presentation.uitll

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
            "🌟 따끈따끈한 후기"
        } else {
                ("-" +it.userId.take(3)+"***"+ "-")
        }
    }
}

@BindingAdapter("releaseYear", "country")
fun TextView.additionalInformation(year: Int, country: String) {
    text = ("$year·$country")
}