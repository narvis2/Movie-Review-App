package com.narvi.delivery.movie.presentation.extension


import java.text.DecimalFormat

fun Float.toDecimalFormatString(format: String): String = DecimalFormat(format).format(this)
