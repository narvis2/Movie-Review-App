package com.narvi.delivery.movie.presentation.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.FeaturedMovie
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.databinding.ItemFeaturedMovieBinding
import com.narvi.delivery.movie.databinding.ItemMovieBinding
import com.narvi.delivery.movie.presentation.extension.dip
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_FEATURED
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_ITEM
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_SECTION_HEADER

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<DataItem> = emptyList()
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_SECTION_HEADER -> {
                TitleItemViewHolder(parent.context)
            }
            ITEM_VIEW_TYPE_FEATURED -> {
                FeaturedMovieItemViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_featured_movie, parent, false)
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                MovieItemViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
                )
            }
            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemValue = data[position].value
        when {
            holder is TitleItemViewHolder && itemValue is String -> {
                holder.bind(itemValue)
            }

            holder is FeaturedMovieItemViewHolder && itemValue is FeaturedMovie -> {
                holder.bind(itemValue)
            }

            holder is MovieItemViewHolder && itemValue is Movie -> {
                holder.bind(itemValue)
            }

            else -> throw RuntimeException("존재하지 않는 ViewHolder 입니다.")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position].value) {
            is String -> {
                ITEM_VIEW_TYPE_SECTION_HEADER
            }
            is FeaturedMovie -> {
                ITEM_VIEW_TYPE_FEATURED
            }
            is Movie -> {
                ITEM_VIEW_TYPE_ITEM
            }
            else -> throw RuntimeException("해당 type 은 존재하지 않습니다.")
        }
    }

    fun addData(featuredMovie: FeaturedMovie?, movies: List<Movie>) {
        val newData = mutableListOf<DataItem>()

        featuredMovie?.let {
            newData += DataItem("🔥 요즘 핫한 영화")
            newData += DataItem(it)
        }

        newData += DataItem("🍿 이 영화들은 보셨나요?")
        newData += movies.map { DataItem(it) }

        data = newData
    }

    inner class TitleItemViewHolder(context: Context) : RecyclerView.ViewHolder(
        TextView(context).apply {
            textSize = 20f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.BLACK)
            setPadding(dip(12f), dip(6f), dip(12f), dip(6f))
        }
    ) {
        fun bind(item: String) {
            (itemView as? TextView)?.text = item
        }
    }

    inner class FeaturedMovieItemViewHolder(
        private val binding: ItemFeaturedMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                (data[adapterPosition].value as? FeaturedMovie)?.movie?.let {
                    onMovieClickListener?.invoke(it)
                }
            }
        }

        fun bind(item: FeaturedMovie) {
            binding.featuredMovie = item

//            item.latestReview?.let { review ->
//                binding.latestReviewLabelTextView.text =
//                    if (review.userId.isNullOrBlank()) {
//                        "🌟 따끈따끈한 후기"
//                    } else {
//                        "- ${review.userId.take(3)}*** -"
//                    }
//            }
        }
    }

    inner class MovieItemViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                (data[adapterPosition].value as? Movie)?.let {
                    onMovieClickListener?.invoke(it)
                }
            }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }

    data class DataItem(val value: Any)
}

