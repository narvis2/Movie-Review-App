package com.narvi.delivery.movie.presentation.ui.review

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.databinding.ItemMovieInformationBinding
import com.narvi.delivery.movie.databinding.ItemReviewBinding
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_TYPE_HEADER
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_TYPE_ITEM

class MovieReviewsAdapter(private val movie: Movie) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviews: List<Review> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            REVIEWS_ITEM_VIEW_TYPE_HEADER -> {
                MovieInformationViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_movie_information,
                        parent,
                        false
                    )
                )
            }
            REVIEWS_ITEM_VIEW_TYPE_ITEM -> {
                ReviewViewHolder(parent)
            }

            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MovieInformationViewHolder -> {
                holder.bind(movie)
            }
            is ReviewViewHolder -> {
                holder.bind(reviews[position - 1])
            }

            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }
    }

    override fun getItemCount(): Int {
        return 1 + reviews.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> REVIEWS_ITEM_VIEW_TYPE_HEADER
            else -> REVIEWS_ITEM_VIEW_TYPE_ITEM
        }
    }

    class MovieInformationViewHolder(
        private val binding : ItemMovieInformationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.movie = item
        }
    }

    inner class ReviewViewHolder(
        parent: ViewGroup,
        private val binding : ItemReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_review,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.review = item
        }
    }
}