package com.narvi.delivery.movie.presentation.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.ReviewedMovie
import com.narvi.delivery.movie.databinding.ItemReviewedMovieBinding

class MyPageAdapter : RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder>() {

    var reviewedMovies: List<ReviewedMovie> = emptyList()
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemReviewedMovieBinding>(
            layoutInflater,
            R.layout.item_reviewed_movie,
            parent,
            false
        )
        return MyPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(reviewedMovies[position])
    }

    override fun getItemCount(): Int {
        return reviewedMovies.size
    }

    inner class MyPageViewHolder(
        private val binding: ItemReviewedMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onMovieClickListener?.invoke(reviewedMovies[adapterPosition].movie)
            }
        }

        fun bind(item: ReviewedMovie) {
            binding.reviewedMovie = item
        }
    }
}